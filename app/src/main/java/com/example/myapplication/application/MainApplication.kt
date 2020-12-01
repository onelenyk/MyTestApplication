package com.example.myapplication.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this

    }



    companion object {
        val context: Context by lazy {
            instance.applicationContext
        }

        lateinit var instance: MainApplication
            private set
    }
}