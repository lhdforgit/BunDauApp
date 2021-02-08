package com.example.bundauapp.presentation.order

import android.content.Context
import android.content.Intent
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.databinding.OrderActBinding

class OrderAct : AbsActivity<OrderViewModel, OrderActBinding>(OrderViewModel::class) {
    override val layoutRes: Int
        get() = R.layout.order_act

    override suspend fun initLayout() {

    }

    override suspend fun initActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, OrderAct::class.java)
        }
    }
}