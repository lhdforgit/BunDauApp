package com.example.bundauapp.presentation.food

import android.content.Context
import android.content.Intent
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.common.ktx.replaceFragment
import com.example.bundauapp.common.ktx.replaceFragmentToBackStack
import com.example.bundauapp.data.room.entity.FoodEntity
import com.example.bundauapp.databinding.FoodActBinding
import com.example.bundauapp.presentation.food.edit.FoodEditorFr
import com.example.bundauapp.presentation.food.list.FoodListFr
import javax.inject.Inject

class FoodAct : AbsActivity<FoodViewModel, FoodActBinding>(FoodViewModel::class), FoodListener {

    @Inject
    lateinit var providerFoodListFr: dagger.Lazy<FoodListFr>

    @Inject
    lateinit var providerFoodEditorFr: dagger.Lazy<FoodEditorFr>

    override val layoutRes: Int
        get() = R.layout.food_act


    override suspend fun initLayout() {
        navigateListFood()
        initData()
    }

    private fun initData() {
        intent?.getBooleanExtra(IS_FOOD, false)?.takeIf { it }?.run {
            binding.toolBar.title = "Danh sách món ăn"
            viewModel.typeFood = 1
        } ?: kotlin.run {
            binding.toolBar.title = "Danh sách nước uống"
            viewModel.typeFood = 2
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

    override fun navigateAddFood(food: FoodEntity?) {
        replaceFragmentToBackStack(providerFoodEditorFr.get(), R.id.container_fr)
    }

    override fun navigateListFood() {
        replaceFragment(providerFoodListFr.get(), R.id.container_fr)
    }
}