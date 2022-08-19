package com.example.contactsrealmdb.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.contactsrealmdb.base.BaseViewModel
import com.example.contactsrealmdb.base.Event
import com.example.contactsrealmdb.data.ContactRepository
import com.example.contactsrealmdb.data.model.Contact
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
            is DataEvent.ContactEdited -> {
                viewModelScope.launch {
                    contactRepository.editContact(event.id, event.name, event.surname, event.number)
                }
                return null
            }
            is DataEvent.LoadContacts -> {
                viewModelScope.launch {
                    val list = contactRepository.getContacts()
                    processDataEvent(DataEvent.OnContactsLoaded(list))
                }
                return null
            }
            is DataEvent.OnContactsLoaded -> {
                Log.d("Check", "size ${event.contacts.size}")
                return previousState.copy(contacts = event.contacts)
            }
            is DataEvent.ContactAdded -> {
                viewModelScope.launch {
                    contactRepository.addContact(event.name, event.surname, event.number)
                }
                return null
            }
            else -> return null
        }
    }
}