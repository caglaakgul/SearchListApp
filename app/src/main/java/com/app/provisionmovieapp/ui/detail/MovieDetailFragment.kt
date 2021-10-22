package com.app.provisionmovieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.app.provisionmovieapp.R
import com.app.provisionmovieapp.databinding.FragmentMovieDetailBinding
import com.app.provisionmovieapp.databinding.FragmentSearchBinding
import com.app.provisionmovieapp.model.ItemDetailData
import com.app.provisionmovieapp.model.Movie
import com.app.provisionmovieapp.ui.list.MovieListFragmentArgs
import com.app.provisionmovieapp.viewmodel.MovieDetailViewModel
import com.app.provisionmovieapp.viewmodel.MovieListViewModel
import com.app.provisionmovieapp.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import org.json.JSONObject
import java.nio.charset.Charset
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {
    val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        arguments?.let {
            viewModel.args = args
            viewModel.movieData = args.movieData
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailViewModel = viewModel
    }

  /*  fun setKeyValue(){
        val json = String(args.movieData, charset())
        val obj = JSONObject(json)
        val getObject = obj.getJSONObject("result")
        val getArray = getObject.getJSONArray("item_size")
        var parameter: String

        var itemSizeArray = ArrayList<ItemDetailData>()

        for (i in 0 until getArray.length()) {
            val objects: JSONObject
            objects = getArray.getJSONObject(i)
            val key = objects["key"].toString()
            val value = objects["value"].toString()
            itemSizeArray.add(ItemDetailData(key = key, value = value))
        }
        var itemString = ""
        itemSizeArray.forEach{data->
            itemString = itemString.plus("${data.key}:${data.value}/")
        }
        itemString = itemString.dropLast(1)
    }*/

}