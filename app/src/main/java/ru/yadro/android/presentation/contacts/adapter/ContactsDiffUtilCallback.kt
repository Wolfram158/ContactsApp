package ru.yadro.android.presentation.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.yadro.android.domain.entity.ContactOrLetter

class ContactsDiffUtilCallback : DiffUtil.ItemCallback<ContactOrLetter>() {
    override fun areItemsTheSame(oldItem: ContactOrLetter, newItem: ContactOrLetter): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: ContactOrLetter, newItem: ContactOrLetter): Boolean {
        return oldItem == newItem
    }
}