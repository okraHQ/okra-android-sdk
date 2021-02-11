package com.okra.widget.example.okhidemo.base

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.okra.widget.example.okhidemo.base.BaseFragment.*

open class BaseBottomSheetDialogFragment: BottomSheetDialogFragment() {
    lateinit var mFragmentNavigation: FragmentNavigation
    lateinit  var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun clearStackAndDismiss(){
        dialog?.dismiss()
        mFragmentNavigation.clearDialogFragmentStack()
    }



    fun showLoading(status:Boolean){
        if (status) alertDialog.show() else alertDialog.dismiss()
    }

    fun showError(errorMessage:String){
        Toast.makeText(requireContext(),errorMessage, Toast.LENGTH_SHORT).show()
        // mFragmentNavigation.openBottomSheet(SuccessBottomSheetFragment.getInstance(false,"Failed",errorMessage))
    }
}