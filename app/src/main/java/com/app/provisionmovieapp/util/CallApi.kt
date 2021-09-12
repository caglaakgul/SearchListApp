package com.app.provisionmovieapp.util

import androidx.lifecycle.MutableLiveData
import com.app.provisionmovieapp.model.Search
import com.app.provisionmovieapp.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

fun getMovieListBySearch(
    apiService: ApiService,
    searchText: String,
    movieList: MutableLiveData<Search>,
    movieError: MutableLiveData<Boolean>,
    movieLoading: MutableLiveData<Boolean>,
    disposable: CompositeDisposable
) {
    movieLoading.value = true
    disposable.add(
        apiService.getListBySearch(searchText)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Search>() {
                override fun onSuccess(t: Search) {
                    movieList.value = t
                    movieError.value = false
                    movieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    movieError.value = true
                    movieLoading.value = false
                    e.printStackTrace()
                }
            })
    )
}