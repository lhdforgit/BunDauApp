package com.example.bundauapp.data.repository.table

import androidx.lifecycle.LiveData
import com.example.bundauapp.data.room.entity.TableEntity

interface TableRepository {
    suspend fun addTable(table: TableEntity)
    suspend fun getListTable(): LiveData<List<TableEntity>>?
    suspend fun deleteTable(tableId: String)
}