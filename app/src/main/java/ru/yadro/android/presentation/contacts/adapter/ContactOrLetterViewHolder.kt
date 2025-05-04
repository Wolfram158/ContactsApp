package ru.yadro.android.presentation.contacts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.yadro.android.domain.entity.ContactOrLetter

sealed class ContactOrLetterViewHolder(
    private val root: View
) : RecyclerView.ViewHolder(root) {
    abstract fun <T : ContactOrLetter> bind(item: T)
}