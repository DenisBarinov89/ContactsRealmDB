package com.example.contactsrealmdb

import com.example.contactsrealmdb.mock.ContactRepository
import com.example.contactsrealmdb.mock.FakeContact
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun contactRepositoryAddingContact() {

        val contactRepository = ContactRepository()
        val contact = FakeContact(
            id = "A098BC",
            name = "TestName",
            surname = "TestSurname",
            number = "9999999"
        )


        //Добавляем контакт в список
        contactRepository.addContact(contact)

        val list = contactRepository.getAllContacts()
        val lastContact = list.last()

        assertEquals(contact, lastContact)

        //Удаляем контакт из списка
        contactRepository.deleteContact(contact)

        assertEquals(0, list.size)
    }

}