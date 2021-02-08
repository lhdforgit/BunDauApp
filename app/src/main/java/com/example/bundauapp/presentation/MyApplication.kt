package com.example.bundauapp.presentation

import com.example.bundauapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    @JvmField
    var WIDTH_SCREEN = 0
    var HEIGHT_SCREEN = 0

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        WIDTH_SCREEN = resources.displayMetrics.widthPixels
        HEIGHT_SCREEN = resources.displayMetrics.heightPixels
    }

    companion object {
        @JvmStatic
        @get:Synchronized
        lateinit var instance: MyApplication
    }
}