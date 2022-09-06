package com.example.contactsrealmdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsrealmdb.data.ContactsDao
import com.example.contactsrealmdb.data.model.Contact

@Database(entities = [Contact::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao
}