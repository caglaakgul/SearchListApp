package com.app.provisionmovieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.app.provisionmovieapp.model.Movie
import com.app.provisionmovieapp.ui.detail.MovieDetailFragmentArgs
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(): ViewModel() {
    var args: MovieDetailFragmentArgs? = null

    var movieData : Movie? = null

}