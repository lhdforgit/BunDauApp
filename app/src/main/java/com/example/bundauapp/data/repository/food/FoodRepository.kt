package com.example.bundauapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.bundauapp.data.room.entity.FoodEntity

interface FoodRepository {
    suspend fun addFood(food: FoodEntity)
    suspend fun getListFood(): LiveData<List<FoodEntity>>?
    suspend fun getListDrink(): LiveData<List<FoodEntity>>?
    suspend fun deleteFood(foodId: String)
}