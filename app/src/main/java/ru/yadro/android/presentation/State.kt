package ru.yadro.android.presentation

sealed class State<T>

class Initial<T>: State<T>()

class Error<T>: State<T>()

class Result<T>(val result: T): State<T>()

