package com.example.bundauapp.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bundauapp.presentation.main.MainViewModel
import com.example.bundauapp.di.common.MyViewModelFactory
import com.example.bundauapp.di.common.ViewModelKey
import com.example.bundauapp.presentation.food.FoodViewModel
import com.example.bundauapp.presentation.food.edit.FoodEditorViewModel
import com.example.bundauapp.presentation.food.list.FoodListViewModel
import com.example.bundauapp.presentation.order.OrderViewModel
import com.example.bundauapp.presentation.setting.SettingViewModel
import com.example.bundauapp.presentation.table.TableViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(viewModel: SettingViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindOrderViewModel(viewModel: OrderViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindTableViewModel(viewModel: TableViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel::class)
    abstract fun bindFoodViewModel(viewModel: FoodViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(FoodListViewModel::class)
    abstract fun bindFoodListViewModel(viewModel: FoodListViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(FoodEditorViewModel::class)
    abstract fun bindFoodEditorViewModel(viewModel: FoodEditorViewModel): ViewModel?
}