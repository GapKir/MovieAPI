package com.dev.di.modules

import com.dev.shared_api.popular_films.PopularFilmsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun providePopularFilmsApi(
        retrofit: Retrofit
    ): PopularFilmsApi = retrofit.create(PopularFilmsApi::class.java)
}