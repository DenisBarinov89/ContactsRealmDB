package com.example.contactsrealmdb

import android.app.Application
import com.example.contactsrealmdb.di.appModule
import com.example.contactsrealmdb.di.dataBaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, dataBaseModule)
        }
    }
}