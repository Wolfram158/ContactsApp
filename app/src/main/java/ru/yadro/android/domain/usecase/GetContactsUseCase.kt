package ru.yadro.android.domain.usecase

import ru.yadro.android.domain.repository.ContactsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    operator fun invoke() = repository.getContacts()
}