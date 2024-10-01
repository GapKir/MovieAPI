package com.example.movieapi.di

import com.dev.shared_repo.popular_films.PopularFilmsRepository
import com.dev.shared_repo.popular_films.PopularFilmsRepositoryImpl

class RepositoriesModule(
    private val networkModule: NetworkModule
) {

    fun getPopularFilmsRepository(): PopularFilmsRepository {
        return PopularFilmsRepositoryImpl(networkModule.getPopularFilmsApi())
    }
}