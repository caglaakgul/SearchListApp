package com.app.provisionmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.provisionmovieapp.model.Search
import com.app.provisionmovieapp.service.ApiService
import com.app.provisionmovieapp.ui.list.MovieListFragmentArgs
import com.app.provisionmovieapp.util.getMovieListBySearch
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    var args: MovieListFragmentArgs? = null

    private val disposable = CompositeDisposable()

    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()
    var movieList = MutableLiveData<Search>()

    fun refreshData(searchText: String) {
        getMovieListBySearch(
            apiService, searchText, movieList,
            movieError,
            movieLoading, disposable
        )
    }
}