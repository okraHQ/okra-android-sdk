package com.okra.widget.example.okhidemo.okhi.permssions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.OkHiDemoActivity
import com.okra.widget.example.okhidemo.base.BaseFragment
import com.okra.widget.example.okhidemo.okhi.SuccessfulFragment
import com.okra.widget.example.okhidemo.utils.ViewPagerObject
import com.okra.widget.example.okhidemo.utils.setUpViewPager
import com.okra.widget.models.OkHiModels.Location
import com.okra.widget.models.OkHiModels.LocationException
import com.okra.widget.models.OkHiModels.User
import com.okra.widget.services.AddressVerificationListener
import com.okra.widget.services.PermissionType
import com.okra.widget.services.PermissionType.*
import kotlinx.android.synthetic.main.fragment_collect_permssision_fragments.*


class CollectPermissionFragment : BaseFragment(),AddressVerificationListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect_permssision_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpUI()
        (requireActivity() as OkHiDemoActivity).addressVerificationService.changeDelegate(this)
    }

    private fun setUpUI() {
        button.setOnClickListener {
            if(viewPager.currentItem == 0){
                if((requireActivity() as OkHiDemoActivity).addressVerificationService.askForOkCollectPermissions()) viewPager.currentItem = 1
            }else if(viewPager.currentItem == 1){
                if((requireActivity() as OkHiDemoActivity).addressVerificationService.askForOkVerifyPermissions()) (requireActivity() as OkHiDemoActivity).addressVerificationService.launchAddressCollection()
            }

        }
    }

    private fun setUpViewPager() {
        val pages = listOf(ViewPagerObject(PermissionsInfoFragment(),""),ViewPagerObject(PermissionsInfoFragment(),""))
        viewPager.setUpViewPager(pages,childFragmentManager)
        dots_indicator.setViewPager(viewPager)
        dots_indicator.dotsClickable = false
    }

    override fun onSuccessCollection(user: User, location: Location) {
        mFragmentNavigation.pushFragment(SuccessfulFragment())
        (requireActivity() as OkHiDemoActivity).addressVerificationService.launchAddressVerification(location)
    }

    override fun onSuccessStartVerification(result: String) {

    }

    override fun onErrorCollectionAction(locationException: LocationException) {
        println(locationException)
    }

    override fun onErrorVerificationAction(locationException: LocationException) {
        println(locationException)
    }

    override fun permissionRequestHandler(result: Boolean, type: PermissionType) {
       when(type){
           OK_COLLECT -> if(result) viewPager.currentItem = 1
           OK_VERIFY -> if(result) (requireActivity() as OkHiDemoActivity).addressVerificationService.launchAddressCollection()
       }
    }

    override fun permissionRequestHandlerError(locationException: LocationException, type: PermissionType) {
        //SHOW ERROR
    }

}