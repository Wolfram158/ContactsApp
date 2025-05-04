package ru.yadro.android.di

import dagger.Binds
import dagger.Module
import ru.yadro.android.data.repository.ContactsRepositoryImpl
import ru.yadro.android.domain.repository.ContactsRepository

@Suppress("UNUSED")
@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindContactsRepository(impl: ContactsRepositoryImpl): ContactsRepository
}