package com.example.contactsrealmdb.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsrealmdb.R
import com.example.contactsrealmdb.data.Contact


class ContactsAdapter(
    private val onDeleteContactClicked: (String) -> Unit
//    private val onEditContactClicked: (String) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    private var contactsData: List<Contact> = emptyList()


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNameAndSurname: TextView
        val tvNumber: TextView
        val ivDelete: ImageView

        init {
            tvNameAndSurname = view.findViewById(R.id.tvNameAndSurname)
            tvNumber = view.findViewById(R.id.tvNumber)
            ivDelete = view.findViewById(R.id.ivDelete)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.tvNameAndSurname.text = "${contactsData[position].name} ${contactsData[position].surname}"
        holder.tvNumber.text = contactsData[position].number


        holder.ivDelete.setOnClickListener {
            Log.d("Check", "DeleteClick")
            onDeleteContactClicked.invoke(contactsData[position].id)
        }
//
//        binding.ivEdit.setOnClickListener {
//            onEditContactClicked.invoke(note.id)
//        }
    }

    override fun getItemCount(): Int = contactsData.size

    fun setData(allContacts: List<Contact>) {
        contactsData = allContacts
        notifyDataSetChanged()

    }
}


//class ContactsAdapter(
//   private val onDeleteContactClicked: (String) -> Unit
////    private val onEditContactClicked: (String) -> Unit
//) :
//    RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {
//
//    private var contactsData: List<Contact> = emptyList()
//
//
//    class MyViewHolder(binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(
//            ItemContactBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        val binding: ItemContactBinding = ItemContactBinding.bind(holder.itemView)
//
//
//
//        with(binding) {
//            tvNameAndSurname.text = "${contactsData[position].name} ${contactsData[position].surname}"
//            tvNumber.text = contactsData[position].number
//        }
//
//        binding.ivDelete.setOnClickListener {
//            Log.d("Check", "DeleteClick")
//            onDeleteContactClicked.invoke(contactsData[position].id)
//        }
////
////        binding.ivEdit.setOnClickListener {
////            onEditContactClicked.invoke(note.id)
////        }
//    }
//
//    fun setData(allContacts: List<Contact>) {
//        contactsData = allContacts
//        notifyDataSetChanged()
//
//    }
//
//    override fun getItemCount(): Int = contactsData.size
//}