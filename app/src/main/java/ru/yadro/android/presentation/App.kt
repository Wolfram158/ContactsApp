package ru.yadro.android.presentation

import android.app.Application
import ru.yadro.android.di.ApplicationComponent
import ru.yadro.android.di.DaggerApplicationComponent

class App : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

}