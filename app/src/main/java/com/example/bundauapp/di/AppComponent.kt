package com.example.bundauapp.di

import android.app.Application
import com.example.bundauapp.data.api.ApiModule
import com.example.bundauapp.data.repository.RepositoryModule
import com.example.bundauapp.data.room.DatabaseModule
import com.example.bundauapp.di.activity.ActivityModule
import com.example.bundauapp.di.viewmodel.ViewModelModule
import com.example.bundauapp.presentation.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}