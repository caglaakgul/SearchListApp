package com.app.provisionmovieapp.di.module

import androidx.lifecycle.ViewModel
import com.app.provisionmovieapp.di.ViewModelKey
import com.app.provisionmovieapp.viewmodel.MovieDetailViewModel
import com.app.provisionmovieapp.viewmodel.MovieListViewModel
import com.app.provisionmovieapp.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
}