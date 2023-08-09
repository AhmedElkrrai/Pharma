package com.example.pharmamanufacturer

import android.app.Application

class PharmaApp : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: PharmaApp
            private set
    }
}