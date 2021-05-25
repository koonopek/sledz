package com.sledz.mobileapp.util

import android.app.Activity
import android.content.Context
import com.sledz.mobileapp.R

object TokenStorage {

    fun get(context: Activity): String? {
        val pref = context.getPreferences(Context.MODE_PRIVATE)

        return pref.getString("token", "")

    }

    fun store(context: Activity, token: String) {
        val pref = context?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (pref.edit()) {
            putString("token", token)
            apply()
        }
    }
}