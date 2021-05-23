package com.sledz.mobileapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SledzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}