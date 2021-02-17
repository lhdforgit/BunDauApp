package com.example.bundauapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.bundauapp.common.JobUtil
import com.example.bundauapp.data.room.dao.FoodDao
import com.example.bundauapp.data.room.entity.FoodEntity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class FoodRepositoryImpl @Inject constructor(private var foodDao: FoodDao) : FoodRepository {
    override suspend fun addFood(food: FoodEntity) {
        JobUtil.doJob(Dispatchers.IO) {
            kotlin.runCatching {
                foodDao.insert(food)
            }
        }
    }

    override suspend fun getListFood(): LiveData<List<FoodEntity>>? {
        return foodDao.getListFood()
    }

    override suspend fun getListDrink(): LiveData<List<FoodEntity>>? {
        return foodDao.getListDrink()
    }

    override suspend fun deleteFood(foodId: String) {
        JobUtil.doJob(Dispatchers.IO) {
            kotlin.runCatching {
                foodDao.deleteFoodById(foodId)
            }
        }
    }
}