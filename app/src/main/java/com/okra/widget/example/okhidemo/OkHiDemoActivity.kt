package com.okra.widget.example.okhidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.okra.widget.example.R
import com.okra.widget.example.okhidemo.base.BaseActivity
import com.okra.widget.example.okhidemo.okhi.AddPersonalInformationFragment
import com.okra.widget.example.okhidemo.okhi.WelcomeScreenFragment
import com.okra.widget.example.okhidemo.okhi.permssions.CollectPermissionFragment

class OkHiDemoActivity : BaseActivity() {

    val baseFragments = lazy {
        listOf(WelcomeScreenFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_hi_demo)
        supportActionBar?.hide()
        initFragNavController(this,baseFragments.value,TAG,supportFragmentManager,R.id.content)
    }

    companion object{
        val TAG = "OkHiDemoActivity"
    }
}