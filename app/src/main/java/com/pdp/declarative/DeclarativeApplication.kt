package com.pdp.declarative

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeclarativeApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}