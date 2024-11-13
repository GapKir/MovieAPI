package com.example.movieapi.di

import com.dev.shared_api.popular_films.PopularFilmsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface ApiModule {

    @Provides
    fun providePopularFilmsApi(
        retrofit: Retrofit
    ): PopularFilmsApi = retrofit.create(PopularFilmsApi::class.java)
}