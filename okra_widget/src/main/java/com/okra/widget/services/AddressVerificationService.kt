package com.okra.widget.services

import android.app.Activity
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import com.okra.widget.models.OkHiModels.*
import io.okhi.android_core.OkHi
import io.okhi.android_core.interfaces.OkHiRequestHandler
import io.okhi.android_core.models.*
import io.okhi.android_okcollect.OkCollect
import io.okhi.android_okcollect.callbacks.OkCollectCallback
import io.okhi.android_okcollect.utilities.OkHiConfig
import io.okhi.android_okcollect.utilities.OkHiTheme
import io.okhi.android_okverify.OkVerify
import io.okhi.android_okverify.interfaces.OkVerifyCallback
import io.okhi.android_okverify.models.OkHiNotification



interface AddressVerificationService {
    fun init(activity: Activity,appColor: String, appIcon:Int,appImageURL: String, appBarColor: String,addressVerificationListener: AddressVerificationListener)
    fun addUser(firstName:String,lastName:String,phoneNumber:String)
    fun launchAddressCollection()
    fun launchAddressVerification(location:Location)
    fun onRequestPermissionsResult( requestCode:Int,permissions:Array<out String>,grantResults:IntArray)
    fun onActivityResult(requestCode:Int,resultCode:Int,data: Intent)
}

class OkHiAddressVerificationServiceImpl :AddressVerificationService{
    var okCollect: OkCollect? = null
    var  okVerify: OkVerify? = null
    var user: OkHiUser? = null
    var okhi: OkHi? = null
    var activity:Activity? = null
    var addressVerificationListener:AddressVerificationListener? = null

    override fun init(activity: Activity,appColor: String, appIcon:Int, appImageURL: String, appBarColor: String,addressVerificationListener: AddressVerificationListener) {
        okhi =  OkHi(activity)
        this.activity = activity
        this.addressVerificationListener = addressVerificationListener
        val okhiAppContext: OkHiAppContext = OkHiAppContext.Builder(OkHiMode.SANDBOX)
                .setAppMeta("Okra", "1.0.0", 1)
                .build()
        val auth = buildAuth(okhiAppContext)
        val theme = buildTheme(appColor, appImageURL, appBarColor)
        val config = buildConfig()
        initOkVerify(auth, activity, appIcon)
        initOkCollect(auth, activity, theme, config)
    }

    private fun initOkCollect(auth: OkHiAuth, activity: Activity, theme: OkHiTheme, config: OkHiConfig) {
        okCollect = OkCollect.Builder(auth, activity)
                .withTheme(theme)
                .withConfig(config)
                .build()
    }

    private fun initOkVerify(auth: OkHiAuth, activity: Activity, appIcon: Int) {
        okVerify = OkVerify.Builder(auth, activity).build()
        val importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) NotificationManager.IMPORTANCE_DEFAULT else 3
        OkVerify.init(activity.applicationContext, OkHiNotification(
                "Verifying your address",
                "We're currently verifying your address. This won't take long",
                "OkHi",
                "OkHi Address Verification",
                "Alerts related to any address verification updates",
                importance,
                appIcon,
                1,  // notificationId
                2 // notification request code
        ))
    }

    override fun addUser(firstName: String, lastName: String, phoneNumber: String) {
         user = OkHiUser.Builder(phoneNumber)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build()
    }

    override fun launchAddressCollection() {
        require(okCollect != null){ "Init before launching"}
        require(addressVerificationListener != null){ "Init before launching"}
        require(user != null){ "Add user before launching"}
        val canStartOkCollect = canStartAddressCreation(Handler(PermissionType.OK_COLLECT, addressVerificationListener!!))
        if(canStartOkCollect) {
            okCollect?.launch(user!!, object : OkCollectCallback<OkHiUser, OkHiLocation> {
                override fun onSuccess(user: OkHiUser, location: OkHiLocation) {
                    println("GOT SUCCESS HEREEE")
                    addressVerificationListener?.onSuccessCollection(user.toUser(),location.toLocation())
                }

                override fun onError(e: OkHiException) {
                   addressVerificationListener?.onErrorCollectionAction(e.toLocationException())
                }
            })
        }
    }



    override fun launchAddressVerification(location:Location) {

        require(okVerify != null){ "Init before launching"}
        require(addressVerificationListener != null){ "Init before launching"}
        require(user != null){ "Add user before launching"}
        val canStartOkVerify = canStartAddressVerification(Handler(PermissionType.OK_VERIFY, addressVerificationListener!!))
        if(canStartOkVerify){
            okVerify!!.start(user, location.toOkHiLocation(), object : OkVerifyCallback<String> {
                override fun onSuccess(result: String) {
                    addressVerificationListener?.onSuccessStartVerification(result)
                    startForegroundVerification()
                }

                override fun onError(e: OkHiException) {
                    addressVerificationListener?.onErrorVerificationAction(e.toLocationException())
                }
            })
        }

    }


    private fun startForegroundVerification() {
        try {
            // start a foreground service that'll improve the stability and reliability of verification signals
            OkVerify.startForegroundService(activity!!.applicationContext)
        } catch (e: OkHiException) {
            e.printStackTrace()
        }
    }
    private fun stopForegroundVerification() {
        // stops the running foreground service
        OkVerify.stopForegroundService(activity!!.applicationContext)
    }

    private fun checkForegroundService(): Boolean {
        // checks if the foreground service is running
        return OkVerify.isForegroundServiceRunning(activity!!.applicationContext)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        okhi?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        okhi?.onActivityResult(requestCode, resultCode, data)
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

    internal class Handler(permissionType: PermissionType, addressVerificationListener: AddressVerificationListener) : OkHiRequestHandler<Boolean> {
        private val permissionType:PermissionType = permissionType
        private val addressVerificationListener:AddressVerificationListener = addressVerificationListener
        override fun onResult(result: Boolean) {
            addressVerificationListener.permissionRequestHandler(result,permissionType)
        }

        override fun onError(exception: OkHiException) {
            addressVerificationListener.permissionRequestHandlerError(exception.toLocationException(),permissionType)
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

    private fun canStartAddressVerification(requestHandler:OkHiRequestHandler<Boolean>): Boolean {
        // Check and request user to enable location services
        val appContext = this.activity!!.applicationContext
        if (!OkHi.isLocationServicesEnabled(appContext)) {
            okhi!!.requestEnableLocationServices(requestHandler)
        } else if (!OkHi.isGooglePlayServicesAvailable(appContext)) {
            // Check and request user to enable google play services
            okhi!!.requestEnableGooglePlayServices(requestHandler)
        } else if (!OkHi.isBackgroundLocationPermissionGranted(appContext)) {
            // Check and request user to grant location permission
            okhi!!.requestBackgroundLocationPermission("Hey we need  background location permissions", "Pretty please..", requestHandler)
        } else {
            return true
        }
        return false
    }



}