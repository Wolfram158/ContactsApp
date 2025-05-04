package ru.yadro.android.presentation.contacts.adapter

import android.content.Context
import android.provider.ContactsContract
import ru.yadro.android.R
import ru.yadro.android.databinding.ItemContactBinding
import ru.yadro.android.domain.entity.Contact
import ru.yadro.android.domain.entity.ContactOrLetter
import java.io.BufferedInputStream

class ContactViewHolder(
    private val binding: ItemContactBinding,
    private val context: Context,
    private val onContactClickListener: ContactsAdapter.OnContactClickListener
) : ContactOrLetterViewHolder(binding.root) {
    override fun <T : ContactOrLetter> bind(item: T) {
        item as Contact
        with(binding) {
            contactName.text = item.name
            contactPhone.text =
                item.mainPhone ?: context.getString(R.string.phone_number_doesnt_exist)
            when (val contactPhotoInputStream =
                ContactsContract.Contacts.openContactPhotoInputStream(
                    context.contentResolver,
                    item.contactUri
                )
            ) {
                null -> contactPhoto.setImageResource(R.drawable.phone)
                else -> {
                    val buf = BufferedInputStream(contactPhotoInputStream)
                    val bitmap = android.graphics.BitmapFactory.decodeStream(buf)
                    contactPhoto.setImageBitmap(bitmap)
                }
            }
            item.mainPhone?.let { phone ->
                root.setOnClickListener {
                    onContactClickListener.onContactClick(phone)
                }
            }
        }
    }

}