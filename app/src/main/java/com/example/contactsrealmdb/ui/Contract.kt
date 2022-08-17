package com.example.contactsrealmdb.ui

import com.example.contactsrealmdb.base.Event
import com.example.contactsrealmdb.data.Contact


data class ViewState(
    val contacts: List<Contact>
)

sealed class UiEvent() : Event {
    object OnAddContactClicked : UiEvent()
    data class OnDeleteContactClicked(val id: String) : UiEvent()
    data class OnEditContactClicked(val index: Int) : UiEvent()
}

sealed class DataEvent() : Event {
    object LoadContacts : DataEvent()
    data class OnContactsLoaded(val contacts: List<Contact>) : DataEvent()
}