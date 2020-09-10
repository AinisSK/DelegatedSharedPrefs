package by.skabs.delegatedsharedprefs

import android.content.SharedPreferences
import androidx.core.os.persistableBundleOf
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import kotlin.reflect.KProperty

/**
 * Delegate provides convenient way of writing and reading SharedPreferences
 */
class PreferencesDelegate<T>(val default: T? = null) {

    val prefs: SharedPreferences = DelegatedSharedPrefs.prefs

    inline operator fun <reified T> getValue(thisRef: Any?, property: KProperty<*>): T {
        return when (T::class) {
            Int::class -> prefs.getInt(property.name, (default as? Int) ?: 0) as T
            String::class -> prefs.getString(property.name, default as? String? ?: "") as T
            Boolean::class -> prefs.getBoolean(property.name, default as? Boolean? ?: false) as T
            PreferenceArrayList::class -> {
                val stringSet = prefs.getStringSet(property.name, emptySet())
                return PreferenceArrayList { list -> setValue(thisRef, property, list) }.apply {
                    removeListener()
                    clear()
                    addAll(stringSet as Set<String>)
                    addListener()
                } as T
            }
            else -> throw IllegalStateException("This type is not implemented yet. Please do that if need")
        }
    }

    inline operator fun <reified T> setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        when (T::class) {
            Int::class -> prefs.edit().putInt(property.name, value as Int).apply()
            String::class -> prefs.edit().putString(property.name, value as String?).apply()
            Boolean::class -> prefs.edit().putBoolean(property.name, value as Boolean).apply()
            PreferenceArrayList::class -> {
                val stringSet = HashSet(value as PreferenceArrayList)
                prefs.edit().putStringSet(property.name, stringSet).apply()
            }
            else -> throw IllegalStateException("This type is not implemented yet. Please do that if need")
        }
    }
}
