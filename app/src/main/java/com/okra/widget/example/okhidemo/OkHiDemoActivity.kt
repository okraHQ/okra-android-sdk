package com.okra.widget.example.okhidemo

import android.os.Bundle
import com.okra.widget.activity.OkHiAddressActivity
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.base.BaseActivity
import com.okra.widget.example.okhidemo.okhi.WelcomeScreenFragment
import com.okra.widget.models.OkHiModels.Location
import com.okra.widget.models.OkHiModels.LocationException
import com.okra.widget.models.OkHiModels.User
import com.okra.widget.services.AddressVerificationListener
import com.okra.widget.services.PermissionType

class OkHiDemoActivity : BaseActivity(),AddressVerificationListener {

    val baseFragments = lazy {
        listOf(WelcomeScreenFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_hi_demo)
        supportActionBar?.hide()
        initFragNavController(this,baseFragments.value,TAG,supportFragmentManager,R.id.content)
        addressVerificationService.init(this,"#3E56FF",R.drawable.geo_icon,"","#3E56FF",this)
    }

    companion object{
        val TAG = "OkHiDemoActivity"
    }

    override fun onSuccessCollection(user: User, location: Location) {

    }

    override fun onSuccessStartVerification(result: String) {

    }

    override fun onErrorCollectionAction(locationException: LocationException) {

    }

    override fun onErrorVerificationAction(locationException: LocationException) {

    }

    override fun permissionRequestHandler(result: Boolean, type: PermissionType) {

    }

    override fun permissionRequestHandlerError(locationException: LocationException, type: PermissionType) {

    }
}