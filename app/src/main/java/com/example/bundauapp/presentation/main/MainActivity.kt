package com.example.bundauapp.presentation.main


import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.databinding.ActivityMainBinding
import com.example.bundauapp.presentation.order.OrderAct
import com.example.bundauapp.presentation.setting.SettingAct
import javax.inject.Inject

class MainActivity @Inject constructor() :
    AbsActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {


    override val layoutRes: Int
        get() = R.layout.activity_main

    override suspend fun initLayout() {
        binding.apply {
            settingBt.setOnClickListener { navigateSetting() }
            orderBt.setOnClickListener { navigateOrder() }
        }
    }

    private fun navigateOrder() {
        startActivity(OrderAct.getIntent(this@MainActivity))
    }

    private fun navigateSetting() {
        startActivity(SettingAct.getIntent(this@MainActivity))
    }

    override suspend fun initActionBar() {

    }
}