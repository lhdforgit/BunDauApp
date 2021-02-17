package com.example.bundauapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bundauapp.data.room.entity.FoodEntity

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg food: FoodEntity)

    @Query("SELECT * FROM food_tb WHERE type = 1 ORDER BY time ASC")
    fun getListFood(): LiveData<List<FoodEntity>>?

    @Query("SELECT * FROM food_tb WHERE type = 2 ORDER BY time ASC")
    fun getListDrink(): LiveData<List<FoodEntity>>?

    @Query("DELETE FROM food_tb  WHERE id = :id")
    fun deleteFoodById(id: String)
}