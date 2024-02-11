package com.example.movieapi.network.response

import com.example.movieapi.network.response.dto.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("language") language: String = "uk-UA",
        @Query("page") page: Int = 1,
        @Query("sort_by") sorting: String = "popularity.desc"
    ): Response<PopularResponse>
}