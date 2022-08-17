package com.example.contactsrealmdb.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.contactsrealmdb.base.BaseViewModel
import com.example.contactsrealmdb.base.Event
import com.example.contactsrealmdb.data.ContactRepository
import kotlinx.coroutines.launch

class MainViewModel(private val contactRepository: ContactRepository) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadContacts)
    }

    override fun initialViewState(): ViewState = ViewState(contacts = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is UiEvent.OnDeleteContactClicked -> {
                viewModelScope.launch {
                    contactRepository.deleteContact(event.id)
                    processDataEvent(DataEvent.LoadContacts)
                }
                return null
            }

            is UiEvent.OnEditContactClicked -> {
                return null
            }
            is DataEvent.LoadContacts -> {
                viewModelScope.launch {
                    val list = contactRepository.getContact()
                    processDataEvent(DataEvent.OnContactsLoaded(list))
                }
                return null

            }
            is DataEvent.OnContactsLoaded -> {
                Log.d("Check", event.contacts.toString())
                return previousState.copy(contacts = event.contacts)
            }
            else -> return null
        }
    }


//    val allContacts: ContactLiveData = ContactLiveData()
//
//    init {
//        Log.d("Check", "view model init")
//        allContacts.value = getAllContacts()
//    }
//
//
//    private fun getAllContacts(): List<Contact> {
//
//        return contactRepository.getContact()
//
//    }
//
//    fun deleteContact(id: String) {
//        contactRepository.deleteContact(id)
//    }


}