package com.okra.widget.services

import com.google.gson.Gson
import io.okhi.android_okcollect.OkCollect


interface AddressVerificationService {
    fun init(appColor: String, appImageURL: String, appBarColor: String)
}

class OkHiAddressVerificationServiceImpl():AddressVerificationService{
    var okCollect: OkCollect? = null
    override fun init(appColor: String, appImageURL: String, appBarColor: String) {

        val okhiAppContext: OkHiAppContext = Builder(OkHiMode.SANDBOX)
                .setAppMeta("My Awesome App", "1.0.0", 1)
                .build()
        val auth = buildAuth()
        val theme = buildTheme()
        val config = buildConfig()
        okCollect = Builder(auth, this)
                .withTheme(theme)
                .withConfig(config)
                .build()
    }

    private fun buildAuth():OkHiAuth{
        return Builder("<my_branch_id>", "<my_client_key>")
                .withContext(okhiAppContext)
                .build()
    }

    private fun buildTheme():OkHiTheme {
        return Builder(appColor)
                .setAppBarLogo(appImageURL)
                .setAppBarColor(appBarColor)
                .build()
    }

    private fun buildConfig():OkHiConfig{
        return Builder()
                .withStreetView()
                .build()
    }


}