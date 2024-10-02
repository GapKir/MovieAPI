package com.example.movieapi.di

import com.dev.feature_popular_films_connector.PopularFilmsConnector
import com.dev.shared_models.popular_films.PopularFilmsVO
import com.dev.shared_repo.popular_films.PopularFilmsRepository

class ConnectorsImplModule(
    private val repositoriesModule: RepositoriesModule
) {

    fun getPopularFilmsDataConnectorImpl(): PopularFilmsConnector {
        return PopularFilmsDataConnectorImpl(repositoriesModule.getPopularFilmsRepository())
    }

}

class PopularFilmsDataConnectorImpl(
    private val popularFilmsRepository: PopularFilmsRepository,
) : PopularFilmsConnector {

    override suspend fun getPopularFilms(): List<PopularFilmsVO?> {
        return popularFilmsRepository.getPopularFilms()
    }
}