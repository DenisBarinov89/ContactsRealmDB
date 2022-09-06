package com.example.contactsrealmdb

import android.app.Application
import com.example.contactsrealmdb.di.AppComponent
import com.example.contactsrealmdb.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }



}
