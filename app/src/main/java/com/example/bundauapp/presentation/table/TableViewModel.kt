package com.example.bundauapp.presentation.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bundauapp.data.repository.table.TableRepository
import com.example.bundauapp.data.room.entity.TableEntity
import java.util.*
import javax.inject.Inject

class TableViewModel @Inject constructor(private val tableRepository: TableRepository) :
    ViewModel() {

    var countTable = 0

    suspend fun addTable() {
        val table = TableEntity(UUID.randomUUID().toString(), countTable + 1, "Bàn ${countTable + 1}")
        tableRepository.addTable(table)
    }

    suspend fun getListTable(): LiveData<List<TableEntity>>? {
        return tableRepository.getListTable()
    }

    suspend fun deleteTable(tableId : String){
        tableRepository.deleteTable(tableId)
    }
}