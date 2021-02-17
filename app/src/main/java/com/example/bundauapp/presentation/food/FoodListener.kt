package com.example.bundauapp.presentation.food

import com.example.bundauapp.data.room.entity.FoodEntity

interface FoodListener {
    fun navigateAddFood(food: FoodEntity?)
    fun navigateListFood()
}