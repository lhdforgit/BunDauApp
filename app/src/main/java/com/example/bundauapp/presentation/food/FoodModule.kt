package com.example.bundauapp.presentation.food

import com.example.bundauapp.di.common.FragmentScoped
import com.example.bundauapp.presentation.food.edit.FoodEditorFr
import com.example.bundauapp.presentation.food.list.FoodListFr
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FoodModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodListFr(): FoodListFr?

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodEditorFr(): FoodEditorFr?
}