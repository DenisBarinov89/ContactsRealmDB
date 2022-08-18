package com.example.contactsrealmdb.data


import android.util.Log
import com.example.contactsrealmdb.data.model.Contact
import java.util.*


class ContactRepositoryImpl(private val contactsDao: ContactsDao) : ContactRepository {

    override suspend fun addContact(name: String?, surname: String?, number: String?) {
        val contact = Contact(
            id = UUID.randomUUID().toString(),
            name = name,
            surname = surname,
            number = number
        )
        contactsDao.create(contact)
    }

    override suspend fun getContacts(): List<Contact> {
        return contactsDao.read()
    }

    override suspend fun deleteContact(id: String) {
        val contactList = contactsDao.getContact(id)
        for (contact in contactList) {
            contactsDao.delete(contact)
        }
    }

    override suspend fun editContact(id: String, name: String?, surname: String?, number: String?) {
        val contact = Contact(id, name, surname, number)
        contactsDao.update(contact)
    }


}