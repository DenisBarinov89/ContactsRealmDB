package com.example.contactsrealmdb.data

import com.example.contactsrealmdb.data.Contact

interface ContactRepository {

    fun addContact(name: String, surname: String, number: String)

    suspend fun getContact(): List<Contact>

    suspend fun deleteContact(id: String)

    fun editContact(id: String, name: String, surname: String, number: String)
}