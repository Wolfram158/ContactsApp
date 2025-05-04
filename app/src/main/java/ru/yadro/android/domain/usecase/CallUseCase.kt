package ru.yadro.android.domain.usecase

import ru.yadro.android.domain.repository.ContactsRepository
import javax.inject.Inject

class CallUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    operator fun invoke(phone: String) = repository.call(phone)
}