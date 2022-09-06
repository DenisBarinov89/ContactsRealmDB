package com.example.contactsrealmdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsrealmdb.App
import com.example.contactsrealmdb.databinding.ActivityAddContactBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding


    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
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