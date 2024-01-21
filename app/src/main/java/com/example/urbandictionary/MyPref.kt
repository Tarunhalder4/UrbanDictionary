package com.example.urbandictionary

import android.content.SharedPreferences
import javax.inject.Inject

class MyPref @Inject constructor(var sharedPreferences: SharedPreferences) {
    fun saveData(key: String, data: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, data).apply()
    }

    fun getData(key: String) = sharedPreferences.getString(key, "")

}