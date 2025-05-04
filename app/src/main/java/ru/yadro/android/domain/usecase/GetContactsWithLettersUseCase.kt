package ru.yadro.android.domain.usecase

import ru.yadro.android.domain.repository.ContactsRepository
import javax.inject.Inject

@Suppress("UNUSED")
class GetContactsWithLettersUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    operator fun invoke() = repository.getContactsWithLetters()
}