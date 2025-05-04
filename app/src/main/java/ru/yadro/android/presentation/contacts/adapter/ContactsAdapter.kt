package ru.yadro.android.presentation.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.yadro.android.databinding.ItemContactBinding
import ru.yadro.android.domain.entity.Contact

class ContactsAdapter(
    private val context: Context,
    private val onContactClickListener: OnContactClickListener
) :
    ListAdapter<Contact, ContactViewHolder>(ContactsDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context, onContactClickListener
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        currentList[position].also { holder.bind(it) }
    }

    fun interface OnContactClickListener {
        fun onContactClick(phone: String)
    }
}