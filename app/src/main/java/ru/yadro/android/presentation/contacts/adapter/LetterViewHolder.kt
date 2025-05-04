package ru.yadro.android.presentation.contacts.adapter

import ru.yadro.android.databinding.ItemLetterBinding
import ru.yadro.android.domain.entity.ContactOrLetter
import ru.yadro.android.domain.entity.Letter

class LetterViewHolder(
    private val binding: ItemLetterBinding,
) : ContactOrLetterViewHolder(binding.root) {
    override fun <T : ContactOrLetter> bind(item: T) {
        item as Letter
        with(binding) {
            letter.text = item.chr.toString()
        }
    }

}