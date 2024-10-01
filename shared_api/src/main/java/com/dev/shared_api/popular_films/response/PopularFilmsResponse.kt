package com.dev.shared_api.popular_films.response

import com.google.gson.annotations.SerializedName


data class PopularFilmsResponse(
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
    )
}

