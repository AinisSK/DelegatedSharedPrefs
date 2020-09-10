package by.skabs.delegatedsharedprefs

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList

/**
 * This class provides ability to listen to Collection changes and update preferences
 * automatically when it happens, so all you need to do is just get collection and
 * work with it. All applied changes will be saved to SharedPreferences
 *
 * @author Ainis Skabs
 * @param updateList function that gonna be called when we need to update
 */
class PreferenceArrayList(updateList: (PreferenceArrayList?) -> Unit) : ObservableArrayList<String>() {

    private val listener = object : ObservableList.OnListChangedCallback<PreferenceArrayList>() {
        override fun onChanged(sender: PreferenceArrayList?) {
            updateList(sender)
        }

        override fun onItemRangeRemoved(sender: PreferenceArrayList?, positionStart: Int, itemCount: Int) {
            updateList(sender)
        }

        override fun onItemRangeMoved(
            sender: PreferenceArrayList?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            updateList(sender)
        }

        override fun onItemRangeInserted(sender: PreferenceArrayList?, positionStart: Int, itemCount: Int) {
            updateList(sender)
        }

        override fun onItemRangeChanged(sender: PreferenceArrayList?, positionStart: Int, itemCount: Int) {
            updateList(sender)
        }

    }

    init {
        addOnListChangedCallback(listener)
    }

    fun addListener() {
        addOnListChangedCallback(listener)
    }

    fun removeListener() {
        removeOnListChangedCallback(listener)
    }
}