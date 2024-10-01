package com.dev.feature_popular_films.di

import com.dev.feature_popular_films_connector.PopularFilmsConnector

interface PopularFilmsDependencies {

    fun getPopularFilmsDataConnectorImpl(): PopularFilmsConnector
}