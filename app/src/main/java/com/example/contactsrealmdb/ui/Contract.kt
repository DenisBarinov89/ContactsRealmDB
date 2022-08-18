package com.example.contactsrealmdb.ui

import com.example.contactsrealmdb.base.Event
import com.example.contactsrealmdb.data.model.Contact


data class ViewState(
    val contacts: List<Contact>
)
sealed class UiEvent() : Event {
    data class OnDeleteContactClicked(val id: String) : UiEvent()
}

sealed class DataEvent() : Event {
    object LoadContacts : DataEvent()
    data class OnContactsLoaded(val contacts: List<Contact>) : DataEvent()
    data class ContactAdded(val name: String?, val surname: String?, val number: String?) : DataEvent()
    data class ContactEdited(val id: String, val name: String?, val surname: String?, val number: String?) : DataEvent()
}