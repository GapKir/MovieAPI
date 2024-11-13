package com.example.movieapi.di

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class NetworkScope

@NetworkScope
@Component(
    modules = [NetworkApiModule::class, ApiModule::class]
)
interface NetworkComponent {
    @Component.Builder
    interface Builder {
        fun build(): NetworkComponent
    }
}

@Module
interface NetworkApiModule {

    @Named("header")
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", ACCEPT_HEADER)
                .addHeader("Authorization", AUTHORIZATION_HEADER)
                .build()
            chain.proceed(request)
        }
    }

    @Named("logger")
    @Provides
    fun provideLoggerInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideGsonFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideHttpClient(
        @Named("header") headerInterceptor: Interceptor,
        @Named("logger") logger: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
            .build()
    }

    @NetworkScope
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
        ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
    }


    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val ACCEPT_HEADER = "application/json"
        private const val AUTHORIZATION_HEADER =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOWI4NjYxOTRmZTRiODJhMjJhNWYxMDZmM2I3Y2M4NyIsInN1YiI6IjY1YzgwYzQ2OTQ1MWU3MDE4NDdiNjU5ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rTI54H7jLKyALGpbgpwVvkTFVkHWUyEBsLMPy4O_pt0"
    }
}