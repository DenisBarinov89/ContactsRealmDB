package com.example.contactsrealmdb.presenter

import com.example.contactsrealmdb.data.ContactRepository


class Presenter(private val contactRepository: ContactRepository) {

    fun addContact(name: String, surname: String, number: String) {
        contactRepository.addContact(name, surname, number)
    }

    fun editContact(id: String, name: String, surname: String, number: String) {
        contactRepository.editContact(id, name, surname, number)
    }



}