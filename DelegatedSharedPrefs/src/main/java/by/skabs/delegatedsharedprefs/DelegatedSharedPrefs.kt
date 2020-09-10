package by.skabs.delegatedsharedprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import kotlin.reflect.KProperty


/**
 * @author Ainis Skabs
 * @param appContext Application context to initialize SharedPreferences instance
 *
 * <p>Wrapper provides convenient way of working with SharedPreferences</p>
 *
 * <p>To add new SharedPreference all you need is just define Kotlin property delegated by
 * @link{PreferencesDelegate} and provide default value</p>
 *
 * <p><b>This class also provides fix for SharedPreferences when we want to store Set of Strings.</b>
 * The bug is, when you get Set<String> from preferences, then change it(add or remove items)
 * and then trying to write it back to Preferences, at runtime everything seems OK, but
 * after app relaunch you'll loose all you changes, cause there is kinda optimisation: due to
 * object of Set<String> remains the same Android does not override it and keeps previous object
 * without applying any changes to it
 * </p>
 */
object DelegatedSharedPrefs {

    private val DEFAULT_NAME = "app_preferences"
    lateinit var prefs: SharedPreferences
        private set
    private var prefsName: String? = null

    fun init(appContext: Context) {
        prefs = appContext.getSharedPreferences(prefsName ?: DEFAULT_NAME, 0)
    }

    fun init(appContext: Context, prefsName: String) {
        this.prefsName = prefsName
        init(appContext)
    }
}