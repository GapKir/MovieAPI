package com.example.movieapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movieapi.R
import com.example.movieapi.databinding.MovieItemBinding
import com.example.movieapi.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder> (){

    var movies: List<Movie> = emptyList()
        set(newValue) {
            val diffCallback = MovieDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
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