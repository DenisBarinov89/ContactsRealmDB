package com.example.contactsrealmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrealmdb.databinding.ActivityMainBinding
import com.example.contactsrealmdb.ui.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()
    private val rvContacts: RecyclerView by lazy { findViewById(R.id.rvContacts) }
    private val adapter: ContactsAdapter by lazy {
        ContactsAdapter(
            { id -> viewModel.processUIEvent(UiEvent.OnDeleteContactClicked(id)) },
            { id ->
                val intent = Intent(this, AddContactActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.viewState.observe(this@MainActivity, ::render)
        rvContacts.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.processUIEvent(DataEvent.LoadContacts)
    }

    private fun render(viewState: ViewState) {
        Log.d("Check", "render")



        adapter.setData(viewState.contacts)
    }
}