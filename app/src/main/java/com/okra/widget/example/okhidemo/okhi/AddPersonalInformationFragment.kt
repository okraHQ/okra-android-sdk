package com.okra.widget.example.okhidemo.okhi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.OkHiDemoActivity
import com.okra.widget.example.okhidemo.base.BaseFragment
import com.okra.widget.example.okhidemo.okhi.permssions.CollectPermissionFragment
import com.okra.widget.example.okhidemo.okhi.permssions.PermissionsInfoFragment
import com.okra.widget.example.okhidemo.utils.Validator
import kotlinx.android.synthetic.main.fragment_add_personal_information.*


class AddPersonalInformationFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_personal_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        etFirstName.doAfterTextChanged { Validator.isValidFirstName(etFirstName) }
        etLastName.doAfterTextChanged { Validator.isValidLastName(etLastName) }
        etPhoneNumber.doAfterTextChanged { Validator.isValidPhone(etPhoneNumber) }
        button2.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
        button.setOnClickListener {
            val isValid = Validator.isValidFirstName(etFirstName) && Validator.isValidLastName(etLastName) && Validator.isValidPhone(etPhoneNumber)
            if(isValid){
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val phoneNumber = etPhoneNumber.text.toString()
                val formattedPhoneNumber = when{
                    phoneNumber.contains("+234") -> phoneNumber
                    phoneNumber.startsWith("0") -> "+234${phoneNumber.substringAfter("0")}"
                    else -> "+234$phoneNumber"
                }
                (requireActivity() as OkHiDemoActivity).addressVerificationService.addUser(firstName, lastName, formattedPhoneNumber)
                mFragmentNavigation.pushFragment(CollectPermissionFragment())
            }

        }
    }

}