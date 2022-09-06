package com.example.contactsrealmdb.di

import android.content.Context
import androidx.room.Room
import com.example.contactsrealmdb.APP_DATABASE
import com.example.contactsrealmdb.AppDataBase
import com.example.contactsrealmdb.MainActivity
import com.example.contactsrealmdb.data.ContactRepository
import com.example.contactsrealmdb.data.ContactRepositoryImpl
import com.example.contactsrealmdb.data.ContactsDao
import com.example.contactsrealmdb.ui.AddContactActivity
import dagger.*
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class, AppBindModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: AddContactActivity)

    val contactsDataBase: AppDataBase
    val contactsDao: ContactsDao

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}

@Module
interface AppBindModule {
    @Binds
    fun bindContactRepositoryImplToContactRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository
}


@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): AppDataBase {
        return Room
            .databaseBuilder(context, AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDataBase): ContactsDao {
        return demoDatabase.contactsDao()
    }

}