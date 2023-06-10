package com.dicoding.mdminsatuapp.data.local

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {
    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_LOCATION_NAME = "location_name"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveLocationName(context: Context, locationName: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_LOCATION_NAME, locationName)
        editor.apply()
    }

    fun getLocationName(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_LOCATION_NAME, null)
    }
}
