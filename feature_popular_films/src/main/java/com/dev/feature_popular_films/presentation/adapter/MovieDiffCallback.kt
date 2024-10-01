package com.dev.feature_popular_films.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dev.shared_models.popular_films.PopularFilmsVO

class MovieDiffCallback: DiffUtil.ItemCallback<PopularFilmsVO>() {

    override fun areItemsTheSame(oldItem: PopularFilmsVO, newItem: PopularFilmsVO): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: PopularFilmsVO, newItem: PopularFilmsVO): Boolean {
        return oldItem == newItem
    }
}