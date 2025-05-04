package ru.yadro.android.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.net.toUri
import ru.yadro.android.data.mapper.getContactOrLetterList
import ru.yadro.android.domain.entity.Contact
import ru.yadro.android.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val context: Context
) : ContactsRepository {
    override fun getContactsWithLetters() = getContacts().getContactOrLetterList()

    override fun getContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val projection =
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
            )
        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI, projection,
            null,
            null,
            SORT_ORDER
        ).use {
            it?.let {
                while (it.moveToNext()) {
                    val id = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val name =
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                    val uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id)
                    var phone: String? = null
                    context.contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        SELECTION_ARG1,
                        arrayOf(id, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE.toString()),
                        null
                    ).use { it1 ->
                        it1?.let {
                            if (it1.moveToNext()) {
                                phone = it1.getString(
                                    it1.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                            }
                        }
                    }
                    contacts.add(Contact(id = id, name = name, contactUri = uri, mainPhone = phone))
                }
            }
        }
        return contacts
    }

    override fun call(phone: String) {
        Intent(Intent.ACTION_CALL, "tel:$phone".toUri()).also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }
    }

    companion object {
        private const val SORT_ORDER = "display_name"
        private const val SELECTION_ARG1 =
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?" +
                    " and ${ContactsContract.CommonDataKinds.Phone.TYPE} = ?"
    }
}