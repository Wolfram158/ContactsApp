package ru.yadro.android.data.mapper

import ru.yadro.android.domain.entity.Contact
import ru.yadro.android.domain.entity.ContactOrLetter
import ru.yadro.android.domain.entity.Letter

fun List<Contact>.getContactOrLetterList(): List<ContactOrLetter> {
    var currentLetter: Char? = null
    val result = mutableListOf<ContactOrLetter>()
    forEach {
        if (currentLetter != it.name.first()) {
            currentLetter = it.name.first()
            result.add(Letter(currentLetter!!))
        }
        result.add(it)
    }
    return result
}