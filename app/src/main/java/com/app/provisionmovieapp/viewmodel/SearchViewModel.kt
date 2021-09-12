package com.app.provisionmovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.provisionmovieapp.model.Search
import com.app.provisionmovieapp.service.ApiService
import com.app.provisionmovieapp.util.getMovieListBySearch
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    var movieList = MutableLiveData<Search>()
    private val disposable = CompositeDisposable()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun getData(searchText: String) {
        getMovieListBySearch(
            apiService, searchText, movieList,
            movieError,
            movieLoading, disposable
        )
    }


}