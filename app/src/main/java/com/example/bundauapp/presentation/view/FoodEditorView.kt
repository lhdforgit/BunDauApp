package com.example.bundauapp.presentation.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.bundauapp.R
import com.example.bundauapp.data.room.entity.FoodEntity
import com.example.bundauapp.databinding.FoodEditorFrBinding
import java.util.*

//class FoodEditorView : FrameLayout {
//
//    private var binding: FoodEditorFrBinding? = null
//    private var foodEntity: FoodEntity? = null
//
//    constructor(context: Context) : super(context) {
//        initView(context)
//    }
//
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
//        initView(context)
//    }
//
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    ) {
//        initView(context)
//    }
//
//    private fun initView(context: Context) {
//        val inflater = LayoutInflater.from(context)
//        binding = DataBindingUtil.inflate(inflater, R.layout.food_editor_fr, null, false)
//        addView(binding?.root)
//    }
//
//    fun setData(food: FoodEntity) {
//        this.foodEntity = food
//
//    }
//
//    fun getData(): FoodEntity {
//        val food = FoodEntity()
//        binding?.apply {
//            food.apply {
//                id = UUID.randomUUID().toString()
//                time = Calendar.getInstance().timeInMillis
//                name = nameEdt.text?.toString() ?: ""
//                price = priceEdt.text?.toString()?.toDouble() ?: 0.0
//                type = if (TextUtils.equals(typeEdt.text?.toString(), "Món ăn")) 1 else 2
//                promoPercent = promoEdt.text?.toString()?.toInt() ?: 0
//                description = descriptionEdt.text?.toString() ?: ""
//            }
//        }
//        return food
//    }
//
//    private fun updateLayout() {
//        foodEntity?.apply {
////            nameEdt.setText(name ?: "")
////            priceEdt.setText(price?.toString() ?: "")
////            promoEdt.setText(promoPercent?.toString() ?: "0" + "%")
////            typeEdt.setText(if (type == 1) "Món ăn" else "Thức uống")
////            descriptionEdt.setText(description ?: "")
//        }
//    }
//
//}