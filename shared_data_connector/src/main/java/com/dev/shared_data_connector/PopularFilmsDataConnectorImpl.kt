package com.dev.shared_data_connector

import com.dev.feature_popular_films_connector.PopularFilmsConnector
import com.dev.shared_models.popular_films.PopularFilmsVO
import com.dev.shared_repo.popular_films.PopularFilmsRepository
import javax.inject.Inject

class PopularFilmsDataConnectorImpl @Inject constructor(
    private val popularFilmsRepository: PopularFilmsRepository,
) : PopularFilmsConnector {

    override suspend fun getPopularFilms(): List<PopularFilmsVO?> {
        return popularFilmsRepository.getPopularFilms()
    }
}