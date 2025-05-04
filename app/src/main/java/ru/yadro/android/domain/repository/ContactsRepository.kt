package ru.yadro.android.domain.repository

import ru.yadro.android.domain.entity.Contact
import ru.yadro.android.domain.entity.ContactOrLetter

interface ContactsRepository {
    fun getContactsWithLetters(): List<ContactOrLetter>

    fun getContacts(): List<Contact>

    fun call(phone: String)
}