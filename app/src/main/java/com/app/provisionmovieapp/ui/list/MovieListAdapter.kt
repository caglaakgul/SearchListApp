package com.app.provisionmovieapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.provisionmovieapp.R
import com.app.provisionmovieapp.databinding.ItemMovieListBinding
import com.app.provisionmovieapp.model.Movie

class MovieListAdapter(val movieList: ArrayList<Movie>, var clickListener: OnItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null

        val binding: ItemMovieListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_list,
                parent,
                false
            )
        viewHolder = MovieListViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.binding.movieViewModel = movieList[position]
        holder.init(movieList.get(position), clickListener)
    }

    class MovieListViewHolder(val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init(item: Movie, action: OnItemClickListener) {
            binding.root.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    fun updateList(newList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: Movie, position: Int)
    }

}