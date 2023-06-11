package com.dicoding.mdminsatuapp.data.local

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {
    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_LOCATION_NAME = "location_name"
    private const val KEY_LATITUDE = "latitude"
    private const val KEY_LONGITUDE = "longitude"

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

    fun saveCoordinates(context: Context, latitude: Double, longitude: Double) {
        val editor = getSharedPreferences(context).edit()
        editor.putFloat(KEY_LATITUDE, latitude.toFloat())
        editor.putFloat(KEY_LONGITUDE, longitude.toFloat())
        editor.apply()
    }

    fun getCoordinates(context: Context): Pair<Double, Double>? {
        val sharedPreferences = getSharedPreferences(context)
        val latitude = sharedPreferences.getFloat(KEY_LATITUDE, 0f).toDouble()
        val longitude = sharedPreferences.getFloat(KEY_LONGITUDE, 0f).toDouble()
        return if (latitude != 0.0 && longitude != 0.0) {
            Pair(latitude, longitude)
        } else {
            null
        }
    }

}
