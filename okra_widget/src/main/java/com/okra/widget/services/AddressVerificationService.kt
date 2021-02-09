package com.okra.widget.services

import android.app.Activity
import android.content.Intent
import io.okhi.android_core.OkHi
import io.okhi.android_core.interfaces.OkHiRequestHandler
import io.okhi.android_core.models.*
import io.okhi.android_okcollect.OkCollect
import io.okhi.android_okcollect.callbacks.OkCollectCallback
import io.okhi.android_okcollect.utilities.OkHiConfig
import io.okhi.android_okcollect.utilities.OkHiTheme


interface AddressVerificationService {
    fun init(activity: Activity,appColor: String, appImageURL: String, appBarColor: String)
    fun addUser(firstName:String,lastName:String,phoneNumber:String)
    fun launchAddressCollection()
    //fun launchAddressCollection(onSuccessCollection:(OkHiUser,OkHiLocation)->Unit,onErrorAction: (OkHiException)->Unit,handler:OkHiRequestHandler<Boolean>)
    fun launchAddressCollection(onSuccessCollection:(OkHiUser,OkHiLocation)->Unit,onErrorAction: (OkHiException)->Unit,handler:OkHiRequestHandler<Boolean>)
    fun onRequestPermissionsResult( requestCode:Int,permissions:Array<out String>,grantResults:IntArray)
    fun onActivityResult(requestCode:Int,resultCode:Int,data: Intent)
}

class OkHiAddressVerificationServiceImpl():AddressVerificationService{
    var okCollect: OkCollect? = null
    var user: OkHiUser? = null
    var okhi: OkHi? = null
    var activity:Activity? = null

    override fun init(activity: Activity,appColor: String, appImageURL: String, appBarColor: String) {
        okhi =  OkHi(activity)
        this.activity = activity
        val okhiAppContext: OkHiAppContext = OkHiAppContext.Builder(OkHiMode.SANDBOX)
                .setAppMeta("Okra", "1.0.0", 1)
                .build()
        val auth = buildAuth(okhiAppContext)
        val theme = buildTheme(appColor, appImageURL, appBarColor)
        val config = buildConfig()
        okCollect = OkCollect.Builder(auth, activity)
                .withTheme(theme)
                .withConfig(config)
                .build()
    }

    override fun addUser(firstName: String, lastName: String, phoneNumber: String) {
         user = OkHiUser.Builder(phoneNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build()
    }

    override fun launchAddressCollection() {
        require(okCollect != null){ "Init before launching"}
        require(user != null){ "Add user before launching"}
        val canStartOkCollect = canStartAddressCreation(Handler())
        if(canStartOkCollect) {
            okCollect?.launch(user!!, object : OkCollectCallback<OkHiUser, OkHiLocation> {
                override fun onSuccess(user: OkHiUser, location: OkHiLocation) {

                }

                override fun onError(e: OkHiException) {
                   // onErrorAction(e)
                }
            })
        }
    }

    override fun launchAddressCollection(onSuccessCollection:(OkHiUser,OkHiLocation)->Unit,onErrorAction: (OkHiException)->Unit,handler:OkHiRequestHandler<Boolean>) {
        require(okCollect != null){ "Init before launching"}
        require(user != null){ "Add user before launching"}
        val canStartOkCollect = canStartAddressCreation(handler)
        if(canStartOkCollect) {
            okCollect?.launch(user!!, object : OkCollectCallback<OkHiUser, OkHiLocation> {
                override fun onSuccess(user: OkHiUser, location: OkHiLocation) {
                    onSuccess(user,location)
                }

                override fun onError(e: OkHiException) {
                    onErrorAction(e)
                }
            })
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        okhi?.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        okhi?.onActivityResult(requestCode, resultCode, data);
    }

    private fun buildAuth(okhiAppContext:OkHiAppContext): OkHiAuth {
        return OkHiAuth.Builder("xbeYujONtY", "8232be5e-15fe-415e-86e7-f4349f8e3a94")
                .withContext(okhiAppContext)
                .build()
    }

    private fun buildTheme(appColor: String, appImageURL: String, appBarColor: String):OkHiTheme {
        return OkHiTheme.Builder(appColor)
                .setAppBarLogo(appImageURL)
                .setAppBarColor(appBarColor)
                .build()
    }

    internal class Handler : OkHiRequestHandler<Boolean> {
        override fun onResult(result: Boolean) {

        }

        override fun onError(exception: OkHiException) {
           // showMessage(exception.message)
        }
    }

    private fun buildConfig(): OkHiConfig {
        return OkHiConfig.Builder()
                .withStreetView()
                .build()
    }

    private fun canStartAddressCreation(requestHandler:OkHiRequestHandler<Boolean>): Boolean {
        // Check and request user to enable location services
        val appContext = this.activity!!.applicationContext
        if (!OkHi.isLocationServicesEnabled(appContext)) {
            okhi!!.requestEnableLocationServices(requestHandler)
        } else if (!OkHi.isGooglePlayServicesAvailable(appContext)) {
            // Check and request user to enable google play services
            okhi!!.requestEnableGooglePlayServices(requestHandler)
        } else if (!OkHi.isLocationPermissionGranted(appContext)) {
            // Check and request user to grant location permission
            okhi!!.requestLocationPermission("Hey we need location permissions", "Pretty please..", requestHandler)
        } else {
            return true
        }
        return false
    }



}