package ru.yadro.android.presentation.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.yadro.android.domain.entity.Contact

class ContactsDiffUtilCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}