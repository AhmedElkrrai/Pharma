package com.example.pharmamanufacturer.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PharmaApp : Application() {

    companion object {
        lateinit var instance: PharmaApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
