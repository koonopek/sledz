package com.sledz.mobileapp.data

import android.content.Context
import android.content.SharedPreferences
import com.sledz.mobileapp.util.Constants.SHARED_PREFERNCES_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

class Store(context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERNCES_KEY, Context.MODE_PRIVATE)
    }

    fun write(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key,value)
            apply()
        }
    }

    fun read(key: String): String {
        return sharedPreferences.getString(key, null) ?: throw Exception("No key [$key] in storage")
    }

}