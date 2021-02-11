package com.okra.widget.example.okhidemo.utils

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class MyViewPageStateAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
    val fragmentTitleList:MutableList<String> = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }

    fun addFragment(fragment: Fragment, title:String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)

    }


}


data class ViewPagerObject(val fragment: Fragment, val title:String)

fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>, fragmentStateManager: FragmentManager){
    val pagerAdapter = MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
        adapter = pagerAdapter
    }
}
