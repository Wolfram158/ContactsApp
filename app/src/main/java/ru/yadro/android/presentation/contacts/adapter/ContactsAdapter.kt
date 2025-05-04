package ru.yadro.android.presentation.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.yadro.android.databinding.ItemContactBinding
import ru.yadro.android.databinding.ItemLetterBinding
import ru.yadro.android.domain.entity.Contact
import ru.yadro.android.domain.entity.ContactOrLetter
import ru.yadro.android.domain.entity.Letter

class ContactsAdapter(
    private val context: Context,
    private val onContactClickListener: OnContactClickListener
) :
    ListAdapter<ContactOrLetter, ContactOrLetterViewHolder>(ContactsDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactOrLetterViewHolder {
        return when (viewType) {
            CONTACT_VIEW_TYPE -> {
                ContactViewHolder(
                    ItemContactBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context, onContactClickListener
                )
            }

            LETTER_VIEW_TYPE -> {
                LetterViewHolder(
                    ItemLetterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw RuntimeException("Undefined viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is Contact -> CONTACT_VIEW_TYPE
            is Letter -> LETTER_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: ContactOrLetterViewHolder, position: Int) {
        currentList[position].also { holder.bind(it) }
    }

    fun interface OnContactClickListener {
        fun onContactClick(phone: String)
    }

    companion object {
        private const val CONTACT_VIEW_TYPE = 1
        private const val LETTER_VIEW_TYPE = 2
    }
}