package com.example.contactsrealmdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsrealmdb.data.ContactsDao
import com.example.contactsrealmdb.data.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao
}