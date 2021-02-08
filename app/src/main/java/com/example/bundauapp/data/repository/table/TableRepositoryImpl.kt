package com.example.bundauapp.data.repository.table

import androidx.lifecycle.LiveData
import com.example.bundauapp.common.JobUtil
import com.example.bundauapp.data.room.dao.TableDao
import com.example.bundauapp.data.room.entity.TableEntity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(private val tableDao: TableDao) : TableRepository {
    override suspend fun addTable(table: TableEntity) {
        JobUtil.doJob(Dispatchers.IO) {
            kotlin.runCatching {
                tableDao.insert(table)
            }
        }
    }

    override suspend fun getListTable(): LiveData<List<TableEntity>>? {
        return tableDao.getListTable()
    }

    override suspend fun deleteTable(tableId: String) {
        JobUtil.doJob(Dispatchers.IO) {
            kotlin.runCatching {
                tableDao.deleteTableById(tableId)
            }
        }
    }
}