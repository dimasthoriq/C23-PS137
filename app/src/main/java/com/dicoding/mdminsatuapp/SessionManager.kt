package com.dicoding.mdminsatuapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveUserId(userId: String) {
        editor.putString("userId", userId)
        editor.apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}
