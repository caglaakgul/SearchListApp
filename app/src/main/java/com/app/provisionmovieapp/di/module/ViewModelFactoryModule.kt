package com.app.provisionmovieapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.app.provisionmovieapp.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}