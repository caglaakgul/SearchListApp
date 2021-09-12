package com.app.provisionmovieapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.provisionmovieapp.R
import com.app.provisionmovieapp.databinding.FragmentMovieListBinding
import com.app.provisionmovieapp.model.Movie
import com.app.provisionmovieapp.model.Search
import com.app.provisionmovieapp.ui.search.SearchFragmentDirections
import com.app.provisionmovieapp.viewmodel.MovieListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment(), MovieListAdapter.OnItemClickListener {
    val args: MovieListFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: MovieListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentMovieListBinding

    private var movieListAdapter = MovieListAdapter(arrayListOf(), this)

    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        arguments?.let {
            viewModel.args = args
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieList.value = args.searchList
        viewModel.movieError.value = args.movieError
        viewModel.movieLoading.value = args.movieLoading

        viewModel.refreshData(args.searchText)

        binding.movieListRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieListAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.movieListRecyclerView.visibility = View.GONE
            binding.listError.visibility = View.GONE
            binding.listProgressBar.visibility = View.VISIBLE
            viewModel.refreshData(args.searchText)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            movieList?.let {
                binding.movieListRecyclerView.visibility = View.VISIBLE
                movieListAdapter.updateList(movieList.resultSearch ?: listOf())
                binding.listProgressBar.visibility = View.GONE
            }
        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) binding.listError.visibility = View.VISIBLE
                else binding.listError.visibility = View.GONE
            }
        })
        viewModel.movieLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) binding.listProgressBar.visibility = View.VISIBLE
                else binding.listProgressBar.visibility = View.GONE
            }
            binding.listProgressBar.visibility = View.GONE
        })
    }

    override fun onItemClick(item: Movie, position: Int) {
        movie = movieListAdapter.movieList[position]

        view?.let {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
            Navigation.findNavController(it).navigate(action)
        }
    }

}