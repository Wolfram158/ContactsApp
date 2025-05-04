package ru.yadro.android.presentation.contacts

import androidx.lifecycle.ViewModel
import ru.yadro.android.domain.usecase.CallUseCase
import ru.yadro.android.domain.usecase.GetContactsUseCase
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val callUseCase: CallUseCase
) : ViewModel() {
    fun getContacts() = getContactsUseCase()

    fun call(phone: String) = callUseCase(phone)
}