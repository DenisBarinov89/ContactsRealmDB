package com.example.contactsrealmdb.data

import com.example.contactsrealmdb.data.model.Contact

interface ContactRepository {

    suspend fun addContact(name: String?, surname: String?, number: String?)

    suspend fun getContacts(): List<Contact>

    suspend fun deleteContact(id: String)

    suspend fun editContact(id: String, name: String?, surname: String?, number: String?)
}