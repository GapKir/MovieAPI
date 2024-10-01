package com.dev.shared_repo.popular_films

import com.dev.shared_api.popular_films.PopularFilmsApi
import com.dev.shared_api.popular_films.response.PopularFilmsResponse
import com.dev.shared_models.popular_films.PopularFilmsVO

interface PopularFilmsRepository {
    suspend fun getPopularFilms(): List<PopularFilmsVO>?
}

class PopularFilmsRepositoryImpl(
    private val popularFilmsApi: PopularFilmsApi,
) : PopularFilmsRepository {

    override suspend fun getPopularFilms(): List<PopularFilmsVO>? {
        val request = popularFilmsApi.getPopularFilms()
        return if (request.isSuccessful){
            request.body()?.toVo()
        } else {
            null
        }
    }


    private fun PopularFilmsResponse.toVo(): List<PopularFilmsVO>? {
        return results?.map {
            PopularFilmsVO(
                overview = it.overview.orEmpty(),
                title = it.title.orEmpty(),
                poster = "https://image.tmdb.org/t/p/original${it.poster}"
            )
        }
    }
}