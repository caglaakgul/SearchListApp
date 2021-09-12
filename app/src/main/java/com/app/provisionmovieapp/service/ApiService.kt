package com.app.provisionmovieapp.service

import com.app.provisionmovieapp.BuildConfig
import com.app.provisionmovieapp.model.Search
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    fun getListBySearch(
        @Query("s") searchTitle: String,
        @Query("apikey") apikey: String = BuildConfig.API_KEY
    ): Single<Search>

    @GET("/")
    fun getDetail(
        @Query("i") movieId: String,
        @Query("apikey") apikey: String = BuildConfig.API_KEY
    ): Single<Search>

}