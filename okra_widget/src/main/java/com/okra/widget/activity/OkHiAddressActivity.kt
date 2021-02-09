package com.okra.widget.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.okra.widget.services.AddressVerificationService
import com.okra.widget.services.OkHiAddressVerificationServiceImpl

open class OkHiAddressActivity: AppCompatActivity() {
     val addressVerificationService:AddressVerificationService = OkHiAddressVerificationServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        addressVerificationService.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        addressVerificationService.onActivityResult(requestCode,resultCode,data ?: Intent())
    }
}