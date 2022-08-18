package com.example.contactsrealmdb.data

import androidx.room.*
import com.example.contactsrealmdb.CONTACTS_TABLE
import com.example.contactsrealmdb.data.model.Contact

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: Contact)

    @Query("SELECT * FROM $CONTACTS_TABLE")
    suspend fun read(): List<Contact>

    @Query("SELECT * FROM $CONTACTS_TABLE WHERE id = :id")
    suspend fun getContact(id: String): List<Contact>

    @Update
    suspend fun update(entity: Contact)

    @Delete
    suspend fun delete(entity: Contact)

}