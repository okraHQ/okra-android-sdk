package com.okra.widget.example.okhidemo.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.okra.widget.activity.OkHiAddressActivity
import com.okra.widget.example.R

open class BaseActivity : OkHiAddressActivity(), BaseFragment.FragmentNavigation, FragNavController.TransactionListener {



    lateinit var basefragNavController: FragNavController


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }



     fun initFragNavController(activity: BaseActivity, listOfRootFragments:List<BaseFragment>, tag:String, supportFragmentManager: FragmentManager, root:Int) {
        basefragNavController  = FragNavController(supportFragmentManager, root)
        basefragNavController.apply {
            transactionListener = activity
            createEager = true
            rootFragments = listOfRootFragments
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                  //  Timber.e(tag, message, throwable)
                }
            }
            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().allowStateLoss(true).customAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right).build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }
        basefragNavController.initialize()
    }


    override fun pushFragment(fragment:Fragment) {
        basefragNavController.pushFragment(fragment)
    }
    override fun popFragments(n: Int) {
           basefragNavController.popFragments(n)
    }

       override fun openDialogFragment(fragment: DialogFragment) {
       }

       override fun popFragment() {
        basefragNavController.popFragment()

    }
    override fun clearDialogFragmentStack() {
        basefragNavController.clearDialogFragment()
    }

    override fun switchFragment(index: Int) {

            basefragNavController.switchTab(index)


    }

    override fun openBottomSheet(bottomSheetFragment: BottomSheetDialogFragment) {
        try{
            basefragNavController.showDialogFragment(bottomSheetFragment)
        }catch (ex: Exception){
            basefragNavController.clearDialogFragment()
            basefragNavController.showDialogFragment(bottomSheetFragment)
        }    }

    override fun clearStack() {
        basefragNavController.clearStack()
    }


    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        supportActionBar?.setDisplayHomeAsUpEnabled(basefragNavController.isRootFragment.not())
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
//        supportActionBar?.setDisplayHomeAsUpEnabled(basefragNavController.isRootFragment.not())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        if(!basefragNavController.isRootFragment){
            if (basefragNavController.popFragment().not()) {
                super.onBackPressed()
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        basefragNavController.onSaveInstanceState(outState)

    }




}