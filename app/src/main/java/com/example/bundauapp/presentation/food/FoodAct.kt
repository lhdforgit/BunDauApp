package com.example.bundauapp.presentation.food

import android.content.Context
import android.content.Intent
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.databinding.FoodActBinding

class FoodAct : AbsActivity<FoodViewModel, FoodActBinding>(FoodViewModel::class) {
    override val layoutRes: Int
        get() = R.layout.food_act

    override suspend fun initLayout() {
        initData()
    }

    private fun initData() {
        intent?.getBooleanExtra(IS_FOOD, false)?.takeIf { it }?.run {
            binding.toolBar.title = "Danh sách món ăn"
        } ?: kotlin.run {
            binding.toolBar.title = "Danh sách nước uống"
        }
    }

    override suspend fun initActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    companion object {
        private const val IS_FOOD = "FoodAct_IS_FOOD"
        fun getIntent(context: Context, isFood: Boolean): Intent {
            val intent = Intent(context, FoodAct::class.java)
            intent.putExtra(IS_FOOD, isFood)
            return intent
        }
    }
}