package com.app.provisionmovieapp.di.module

import com.app.provisionmovieapp.ui.detail.MovieDetailFragment
import com.app.provisionmovieapp.ui.list.MovieListFragment
import com.app.provisionmovieapp.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}