package com.example.bundauapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bundauapp.data.room.dao.FoodDao
import com.example.bundauapp.data.room.dao.TableDao
import com.example.bundauapp.data.room.entity.FoodEntity
import com.example.bundauapp.data.room.entity.TableEntity

@Database(
    entities = [TableEntity::class, FoodEntity::class],
    version = 1,
    exportSchema = true
)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class DatabaseMain : RoomDatabase() {
    abstract fun tableDao(): TableDao
    abstract fun foodDao() : FoodDao
}