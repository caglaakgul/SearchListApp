package com.app.provisionmovieapp.di

import android.app.Application
import com.app.provisionmovieapp.base.MyApplication
import com.app.provisionmovieapp.di.module.ActivityBuildersModule
import com.app.provisionmovieapp.di.module.AppModule
import com.app.provisionmovieapp.di.module.NetworkModule
import com.app.provisionmovieapp.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}