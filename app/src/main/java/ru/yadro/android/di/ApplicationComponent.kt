package ru.yadro.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.yadro.android.presentation.contacts.ContactsFragment

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(contactsFragment: ContactsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}