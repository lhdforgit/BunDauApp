package com.example.bundauapp.data.room

import android.app.Application
import androidx.room.Room
import com.example.bundauapp.data.room.dao.TableDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    private const val DATABASE_NAME = "bun_dau.db"

    @Singleton
    @Provides
    fun provideDb(app: Application): DatabaseMain {
        return Room.databaseBuilder(app, DatabaseMain::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTableDao(db: DatabaseMain): TableDao {
        return db.tableDao()
    }
}