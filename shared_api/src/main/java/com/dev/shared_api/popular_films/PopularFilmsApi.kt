package com.dev.shared_api.popular_films

import com.dev.shared_api.popular_films.response.PopularFilmsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularFilmsApi {

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("language") language: String = "uk-UA",
        @Query("page") page: Int = 1,
        @Query("sort_by") sorting: String = "popularity.desc"
    ): Response<PopularFilmsResponse>
}