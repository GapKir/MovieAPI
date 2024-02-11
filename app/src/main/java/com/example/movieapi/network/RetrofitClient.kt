package com.example.movieapi.network

import com.example.movieapi.network.response.MovieAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val ACCEPT_HEADER = "application/json"
    private const val AUTHORIZATION_HEADER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOWI4NjYxOTRmZTRiODJhMjJhNWYxMDZmM2I3Y2M4NyIsInN1YiI6IjY1YzgwYzQ2OTQ1MWU3MDE4NDdiNjU5ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rTI54H7jLKyALGpbgpwVvkTFVkHWUyEBsLMPy4O_pt0"

    private val headerInterceptor = Interceptor{chain->
        val request = chain.request().newBuilder()
            .addHeader("accept", ACCEPT_HEADER)
            .addHeader("Authorization", AUTHORIZATION_HEADER)
            .build()
        chain.proceed(request)
    }

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieAPI = retrofit.create(MovieAPI::class.java)
}