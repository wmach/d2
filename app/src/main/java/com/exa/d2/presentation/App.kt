package com.exa.d2.presentation

import android.util.Log
import com.exa.d2.di.DaggerAppComponent
import com.exa.d2.di.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App : DaggerApplication() {

    companion object {
        const val TAG = "App"
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Log.e(TAG, "=== entering applicastionInjector()")
        val result = DaggerAppComponent.builder()
                .application(this)
                .databaseModule(DatabaseModule.instance)
                .build()
        Log.e(TAG, "--- leaving applicastionInjector()")
        return result
    }
}