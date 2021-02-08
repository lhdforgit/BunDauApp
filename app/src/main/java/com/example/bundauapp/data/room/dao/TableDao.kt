package com.example.bundauapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bundauapp.data.room.entity.TableEntity

@Dao
interface TableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg search: TableEntity)

    @Query("SELECT * FROM table_tb ORDER BY number ASC")
    fun getListTable(): LiveData<List<TableEntity>>?

    @Query("DELETE FROM table_tb  WHERE id = :id")
    fun deleteTableById(id: String)
}