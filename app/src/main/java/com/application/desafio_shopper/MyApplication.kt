package com.application.desafio_shopper

import android.app.Application
import android.util.Log
import com.application.desafio_shopper.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    val googleApiKey: String = "AIzaSyCkLFTR2W7ubSgHnicZk7Ij8hInZzfhXCE"

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}