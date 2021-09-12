package com.app.provisionmovieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    @SerializedName("Search")
    val resultSearch: List<Movie>? = null
): Parcelable