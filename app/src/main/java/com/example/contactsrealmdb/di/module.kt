package com.example.contactsrealmdb.di


import androidx.room.Room
import com.example.contactsrealmdb.APP_DATABASE
import com.example.contactsrealmdb.AppDataBase
import com.example.contactsrealmdb.data.ContactRepository
import com.example.contactsrealmdb.data.ContactRepositoryImpl

import com.example.contactsrealmdb.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<ContactRepository> {
        ContactRepositoryImpl(contactsDao = get())
    }
    viewModel {
        MainViewModel(contactRepository = get())
    }
}

val dataBaseModule = module {
    single {
        Room
            .databaseBuilder(androidContext(), AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDataBase>().contactsDao()
    }

}