package com.dev.popular_films.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dev.popular_films.databinding.MovieItemBinding
import com.dev.popular_films.presentation.adapter.MovieDiffCallback
import com.example.movieapi.R
import com.example.movieapi.model.Movie

class MovieAdapter
    : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class MovieViewHolder(
        private val binding: MovieItemBinding
    ): ViewHolder(binding.root){

        fun bind(item: Movie){
            binding.tvTitle.text = item.title
            binding.tvDesc.text = item.overview

            Glide.with(binding.ivPoster.context)
                .load(item.poster)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivPoster)
        }
    }
}