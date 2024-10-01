package com.example.movieapi.di

import com.dev.feature_popular_films.di.PopularFilmsDependencies
import com.dev.feature_popular_films_connector.PopularFilmsConnector

class FeatureComponent: PopularFilmsDependencies{

    private val networkModule: NetworkModule = NetworkModule()
    private val repositoriesModule: RepositoriesModule = RepositoriesModule(networkModule)
    private val connectorsImplModule: ConnectorsImplModule = ConnectorsImplModule(repositoriesModule)

    override fun getPopularFilmsDataConnectorImpl(): PopularFilmsConnector {
        return connectorsImplModule.getPopularFilmsDataConnectorImpl()
    }
}