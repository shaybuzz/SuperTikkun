package com.supertikkun.view

import android.app.Application
import timber.log.Timber

class SuperTikkunApp : Application() {

    override fun onCreate() {
        super.onCreate()

        init();
    }

    fun init(){
        Timber.plant(Timber.DebugTree())
    }
}