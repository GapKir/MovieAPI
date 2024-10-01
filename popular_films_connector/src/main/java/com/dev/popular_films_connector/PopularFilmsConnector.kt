package com.dev.popular_films_connector

import com.dev.shared_models.popular_films.PopularFilmsVO

interface PopularFilmsConnector {
    suspend fun getPopularFilms(): List<PopularFilmsVO>?
}