package com.example.contactsrealmdb.di


import com.example.contactsrealmdb.data.ContactRepository
import com.example.contactsrealmdb.data.ContactRepositoryImpl
import com.example.contactsrealmdb.presenter.Presenter
import com.example.contactsrealmdb.ui.MainViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())

        val configuration = RealmConfiguration.Builder()
            .name("file.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        Realm.getDefaultInstance()
    }

    single<ContactRepository> {
        ContactRepositoryImpl(realm = get())
    }

    viewModel {
        MainViewModel(contactRepository = get())
    }

    single {
        Presenter(contactRepository = get())
    }
}