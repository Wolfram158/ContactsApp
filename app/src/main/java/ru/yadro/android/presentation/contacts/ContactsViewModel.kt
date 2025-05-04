package ru.yadro.android.presentation.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yadro.android.domain.entity.ContactOrLetter
import ru.yadro.android.domain.usecase.CallUseCase
import ru.yadro.android.domain.usecase.GetContactsWithLettersUseCase
import ru.yadro.android.presentation.Error
import ru.yadro.android.presentation.Initial
import ru.yadro.android.presentation.Result
import ru.yadro.android.presentation.State
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val getContactsWithLetterUseCase: GetContactsWithLettersUseCase,
    private val callUseCase: CallUseCase
) : ViewModel() {
    private val _contacts = MutableStateFlow<State<List<ContactOrLetter>>>(Initial())
    val contacts = _contacts.asStateFlow()

    fun emitContacts() {
        viewModelScope.launch {
            try {
                getContactsWithLetterUseCase().also {
                    _contacts.emit(Result(it))
                }
            } catch (_: Exception) {
                _contacts.emit(Error())
            }
        }
    }

    fun call(phone: String) = callUseCase(phone)
}