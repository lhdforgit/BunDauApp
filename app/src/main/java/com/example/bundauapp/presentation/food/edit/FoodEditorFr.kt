package com.example.bundauapp.presentation.food.edit

import com.example.bundauapp.R
import com.example.bundauapp.common.AbsFragment
import com.example.bundauapp.common.ktx.setPriceFormat
import com.example.bundauapp.databinding.FoodEditorFrBinding
import javax.inject.Inject

class FoodEditorFr @Inject constructor() :
    AbsFragment<FoodEditorViewModel, FoodEditorFrBinding>(FoodEditorViewModel::class) {
    override val layoutRes: Int
        get() = R.layout.food_editor_fr

    override suspend fun initLayout() {
        initAction()
    }

    private fun initAction() {
        binding.apply {
            priceEdt.setPriceFormat()
        }
    }
}