package com.example.bundauapp.presentation.setting

import android.content.Context
import android.content.Intent
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.databinding.SettingActBinding
import com.example.bundauapp.presentation.food.FoodAct
import com.example.bundauapp.presentation.table.TableAct

class SettingAct : AbsActivity<SettingViewModel, SettingActBinding>(SettingViewModel::class) {
    override val layoutRes: Int
        get() = R.layout.setting_act

    override suspend fun initLayout() {
        initAction()
    }

    private fun initAction() {
        binding.apply {
            tableBt.setOnClickListener { navigateTable() }
            foodBt.setOnClickListener { navigateFood() }
            drinkBt.setOnClickListener { navigateDrink() }
        }
    }

    private fun navigateDrink() {
        startActivity(FoodAct.getIntent(this@SettingAct, false))
    }

    private fun navigateFood() {
        startActivity(FoodAct.getIntent(this@SettingAct, true))
    }

    private fun navigateTable() {
        startActivity(TableAct.getIntent(this@SettingAct))
    }

    override suspend fun initActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SettingAct::class.java)
        }
    }
}