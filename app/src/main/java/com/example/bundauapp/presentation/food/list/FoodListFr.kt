package com.example.bundauapp.presentation.food.list

import android.app.Activity
import android.content.Context
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsFragment
import com.example.bundauapp.databinding.FoodListFrBinding
import com.example.bundauapp.presentation.food.FoodListener
import javax.inject.Inject

class FoodListFr @Inject constructor() :
    AbsFragment<FoodListViewModel, FoodListFrBinding>(FoodListViewModel::class) {

    private lateinit var listener: FoodListener

    override val layoutRes: Int
        get() = R.layout.food_list_fr


    override suspend fun initLayout() {
        initAction()
    }

    private fun initAction() {
        binding.apply {
            addFoodBt.setOnClickListener { handleAddFood() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FoodListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement FoodListener")
        }
    }

    private fun handleAddFood() {
        listener.navigateAddFood(null)
    }
}