package ru.yadro.android.domain.entity

import android.net.Uri

data class Contact(
    val id: String,
    val name: String,
    val contactUri: Uri,
    val mainPhone: String? = null,
)