package by.skabs.prefsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.skabs.delegatedsharedprefs.PreferencesDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countPref: Int by PreferencesDelegate<Int>()
        countPref = 20
        Log.i("Prefs", "count is $countPref")

        with(AppPreferences()) {
            studentsListPref.add("Ivanov")
            studentsListPref.add("Smirnov")
            studentsListPref.forEach { Log.i("Prefs", "Student surname: $it") }
        }
    }
}