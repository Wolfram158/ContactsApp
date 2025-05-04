package ru.yadro.android.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.yadro.android.presentation.contacts.ContactsViewModel

@Suppress("UNUSED")
@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(ContactsViewModel::class)
    fun bindContactsViewModel(contactsViewModel: ContactsViewModel): ViewModel
}