package ru.yadro.android.domain.repository

import ru.yadro.android.domain.entity.Contact

interface ContactsRepository {
    fun getContacts(): List<Contact>

    fun call(phone: String)
}