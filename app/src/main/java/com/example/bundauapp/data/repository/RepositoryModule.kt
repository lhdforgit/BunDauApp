package com.example.bundauapp.data.repository

import com.example.bundauapp.data.repository.food.FoodRepository
import com.example.bundauapp.data.repository.food.FoodRepositoryImpl
import com.example.bundauapp.data.repository.post.PostRepository
import com.example.bundauapp.data.repository.post.PostRepositoryImpl
import com.example.bundauapp.data.repository.table.TableRepository
import com.example.bundauapp.data.repository.table.TableRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl): PostRepository

    @Singleton
    @Binds
    abstract fun bindTableRepository(repository: TableRepositoryImpl): TableRepository

    @Singleton
    @Binds
    abstract fun bindFoodRepository(repository: FoodRepositoryImpl): FoodRepository

}