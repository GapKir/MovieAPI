package com.dev.popular_films.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapi.model.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}