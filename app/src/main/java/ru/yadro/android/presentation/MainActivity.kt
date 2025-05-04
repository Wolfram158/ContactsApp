package ru.yadro.android.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ru.yadro.android.R
import ru.yadro.android.presentation.contacts.ContactsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchWithPermissionsRequest()
    }

    private fun launchWithPermissionsRequest() {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.containsValue(false)) {
                finish()
                return@registerForActivityResult
            }
            launchContactsFragment()
        }.launch(arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS))
    }


    private fun launchContactsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContactsFragment.newInstance()).commit()
    }
}