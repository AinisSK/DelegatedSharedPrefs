package by.skabs.prefsexample

import android.app.Application
import by.skabs.delegatedsharedprefs.DelegatedSharedPrefs

class PrefsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DelegatedSharedPrefs.init(applicationContext)
    }
}