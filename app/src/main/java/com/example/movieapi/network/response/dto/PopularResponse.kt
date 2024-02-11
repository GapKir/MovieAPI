package com.example.movieapi.network.response.dto

import com.example.movieapi.model.Movie
import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("results")
    val results: List<Result>?

) {
    data class Result(
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("poster_path")
        val poster: String?,
        @SerializedName("title")
        val title: String?
    ) {
        fun toMovie(): Movie {
            return Movie(
                overview = overview ?: "",
                title = title ?: "",
                poster = "https://image.tmdb.org/t/p/original$poster"
            )
        }
    }
}

