package com.example.contactsrealmdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsrealmdb.databinding.ActivityAddContactBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etName.requestFocus()

        binding.btnSave.setOnClickListener {

            val id: String? = intent.getStringExtra("id")
            if (id.isNullOrEmpty()) {
                with(binding) {
                    viewModel.processUIEvent(
                        DataEvent.ContactAdded(
                            name = etName.text.toString(),
                            surname = etSurname.text.toString(),
                            number = etNumber.text.toString()
                        )
                    )
                }
            } else {
                with(binding) {
                    viewModel.processUIEvent(
                        DataEvent.ContactEdited(
                            id = id,
                            name = etName.text.toString(),
                            surname = etSurname.text.toString(),
                            number = etNumber.text.toString()
                        )
                    )
                }
            }
            finish()
        }
    }
}