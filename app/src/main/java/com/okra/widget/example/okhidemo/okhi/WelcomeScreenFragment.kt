package com.okra.widget.example.okhidemo.okhi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome_screen.*


class WelcomeScreenFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        button.setOnClickListener {
            mFragmentNavigation.pushFragment(AddPersonalInformationFragment())
        }
    }


}