package com.example.contactsrealmdb.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactsrealmdb.CONTACTS_TABLE
import java.util.*

@Entity(tableName = CONTACTS_TABLE)
data class Contact(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "surname")
    val surname: String?,

    @ColumnInfo(name = "number")
    val number: String?
)
