package com.example.contactsrealmdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsrealmdb.databinding.ActivityAddContactBinding
import com.example.contactsrealmdb.presenter.Presenter

import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val id : String? = intent.getStringExtra("id")

            if (id.isNullOrEmpty()) {
                with(binding) {
                    presenter.addContact(
                        name = etName.text.toString(),
                        surname = etSurname.text.toString(),
                        number = etNumber.text.toString()
                    )
                }
            } else {
                with(binding) {
                    presenter.editContact(
                        id = id,
                        name = etName.text.toString(),
                        surname = etSurname.text.toString(),
                        number = etNumber.text.toString()
                    )
                }
            }
            finish()
        }
    }

//    override fun onAddContact(contacts: List<Contact>) {
//        Toast.makeText(this, contacts.last().name, Toast.LENGTH_SHORT).show()
//    }
}