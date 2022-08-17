package com.example.contactsrealmdb.data


import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import java.util.*


class ContactRepositoryImpl(
    private val realm: Realm
) : ContactRepository {

    override fun addContact(name: String, surname: String, number: String) {
        realm.executeTransaction {
            it.createObject(Contact::class.java, UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }
            Log.d("Check", "Object Created")
        }
    }

    override suspend fun getContact(): List<Contact> {
        return realm.where(Contact::class.java).findAll()
    }

    override suspend fun deleteContact(id: String) {
        Log.d("Check", "Object Deleted1")
        realm.executeTransaction { transactionRealm ->
            val result: RealmResults<Contact>  = transactionRealm.where(Contact::class.java).equalTo("id", id).findAll()
            result.deleteFirstFromRealm()

        }
        Log.d("Check", "Object Deleted2")
    }

    override fun editContact(id: String, name: String, surname: String, number: String) {


        realm.beginTransaction()
        val contact = realm.where(Contact::class.java).equalTo("id", id).findFirst()
        contact?.name = name
        contact?.surname = surname
        contact?.number = number
        if (contact != null) {
            realm.insertOrUpdate(contact)
        }
        realm.commitTransaction()
    }

}