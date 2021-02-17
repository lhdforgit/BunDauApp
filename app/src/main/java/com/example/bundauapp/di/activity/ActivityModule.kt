package com.example.bundauapp.di.activity

import com.example.bundauapp.presentation.main.MainActivity
import com.example.bundauapp.di.common.ActivityScoped
import com.example.bundauapp.presentation.food.FoodAct
import com.example.bundauapp.presentation.food.FoodModule
import com.example.bundauapp.presentation.order.OrderAct
import com.example.bundauapp.presentation.setting.SettingAct
import com.example.bundauapp.presentation.table.TableAct
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainAct() : MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun settingAct() : SettingAct

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun orderAct() : OrderAct

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun tableAct() : TableAct

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FoodModule::class])
    abstract fun foodAct() : FoodAct
}