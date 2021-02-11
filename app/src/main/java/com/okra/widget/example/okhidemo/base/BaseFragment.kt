package com.okra.widget.example.okhidemo.base


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dmax.dialog.SpotsDialog


open class BaseFragment : Fragment() {


    lateinit var mFragmentNavigation: FragmentNavigation
    lateinit  var alertDialog: AlertDialog





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context
            alertDialog = SpotsDialog.Builder()
                    .setContext(context)
                    .setMessage("Loading...")
                    .setCancelable(false)
                    .build()

        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
        fun popFragment()
        fun popFragments(n:Int)
        fun openDialogFragment(fragment:DialogFragment)
        fun openBottomSheet(bottomSheetDialogFragment: BottomSheetDialogFragment)
        fun clearStack()
        fun clearDialogFragmentStack()
        fun switchFragment(index: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun showloadingDialog(show:Boolean){
        if(show){
            alertDialog.show()
        } else {
            alertDialog.hide()

        }

    }



    fun showLoading(status:Boolean){
        when(val activity = requireActivity()){
//            is TeenMainActivity -> activity.showLoading(status)
//            is IntroActivity -> activity.showLoading(status)
            else ->  if (status) alertDialog.show() else alertDialog.dismiss()
        }

    }

    fun showError(errorMessage:String){
        println("SHOW ERROR ${errorMessage}")
        //mFragmentNavigation.openBottomSheet(SuccessBottomSheetFragment.newInstance("Error!", errorMessage, false))
       Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
    }

}
