package ru.yadro.android.domain.entity

data class Letter(val chr: Char) : ContactOrLetter(chr.toString())