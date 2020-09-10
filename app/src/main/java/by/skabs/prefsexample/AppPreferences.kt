package by.skabs.prefsexample

import by.skabs.delegatedsharedprefs.PreferenceArrayList
import by.skabs.delegatedsharedprefs.PreferencesDelegate

class AppPreferences {

    val studentsListPref: PreferenceArrayList by PreferencesDelegate<PreferenceArrayList>()
}