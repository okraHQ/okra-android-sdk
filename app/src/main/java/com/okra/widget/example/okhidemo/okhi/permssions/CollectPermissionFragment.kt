package com.okra.widget.example.okhidemo.okhi.permssions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.base.BaseFragment
import com.okra.widget.example.okhidemo.utils.ViewPagerObject
import com.okra.widget.example.okhidemo.utils.setUpViewPager
import kotlinx.android.synthetic.main.fragment_collect_permssision_fragments.*


class CollectPermissionFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect_permssision_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpUI()
    }

    private fun setUpUI() {
        button.setOnClickListener {
            viewPager.currentItem = 1
        }
    }

    private fun setUpViewPager() {
        val pages = listOf(ViewPagerObject(PermissionsInfoFragment(),""),ViewPagerObject(PermissionsInfoFragment(),""))
        viewPager.setUpViewPager(pages,childFragmentManager)
        dots_indicator.setViewPager(viewPager)
        dots_indicator.dotsClickable = false
    }

}