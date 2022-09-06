package com.example.contactsrealmdb.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrealmdb.R
import com.example.contactsrealmdb.data.model.Contact


class ContactsAdapter(
    private val onDeleteContactClicked: (String) -> Unit,
    private val onEditContactClicked: (String) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    private var contactsData: List<Contact> = emptyList()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNameAndSurname: TextView
        val tvNumber: TextView
        val ivDelete: ImageView
        val ivEdit: ImageView

        init {
            tvNameAndSurname = view.findViewById(R.id.tvNameAndSurname)
            tvNumber = view.findViewById(R.id.tvNumber)
            ivDelete = view.findViewById(R.id.ivDelete)
            ivEdit = view.findViewById(R.id.ivEdit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvNameAndSurname.text =
            "${contactsData[position].name} ${contactsData[position].surname}"
        holder.tvNumber.text = contactsData[position].number

        holder.ivDelete.setOnClickListener {
            Log.d("Check", "$position")
            onDeleteContactClicked.invoke(contactsData[position].id)

        }

        holder.ivEdit.setOnClickListener {
            onEditContactClicked.invoke(contactsData[position].id)
        }
    }

    override fun getItemCount(): Int {

        return contactsData.size
    }

    class CallbackContact(private val oldList: List<Contact>, private val newList: List<Contact>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            newList[newItemPosition] == oldList[oldItemPosition]

    }


    fun setData(allContacts: List<Contact>) {
        val oldList = contactsData
        contactsData = allContacts
        val result = DiffUtil.calculateDiff(CallbackContact(oldList, contactsData))
        result.dispatchUpdatesTo(this)
    }


}