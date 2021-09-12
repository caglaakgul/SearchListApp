package com.app.provisionmovieapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.provisionmovieapp.R
import com.app.provisionmovieapp.databinding.FragmentSearchBinding
import com.app.provisionmovieapp.viewmodel.SearchViewModel
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.app.provisionmovieapp.base.MyApplication
import com.app.provisionmovieapp.di.AppComponent
import com.app.provisionmovieapp.model.Search
import com.app.provisionmovieapp.ui.list.MovieListFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*

class SearchFragment : DaggerFragment() {
    @Inject
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSearchBinding

    lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSearch.setOnClickListener {
            text = binding.searchView.getQuery().toString()
            viewModel.getData(text)
            observeLiveData()
        }
    }

    private fun observeLiveData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            movieList?.let {
                view?.let {
                    val action = SearchFragmentDirections.actionSearchFragmentToMovieListFragment(
                        viewModel.movieList.value,
                        viewModel.movieError.value ?: false,
                        viewModel.movieLoading.value ?: false,
                        text
                    )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}