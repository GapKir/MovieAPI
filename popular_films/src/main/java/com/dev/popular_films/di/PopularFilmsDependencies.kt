package com.dev.popular_films.di

import com.dev.popular_films_connector.PopularFilmsConnector

interface PopularFilmsDependencies {

    fun getPopularFilmsDataConnectorImpl(): PopularFilmsConnector
}