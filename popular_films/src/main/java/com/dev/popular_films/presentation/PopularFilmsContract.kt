package com.dev.popular_films.presentation

import com.dev.shared_models.popular_films.PopularFilmsVO

object PopularFilmsContract {

    sealed interface Event
    sealed interface Effect

    sealed interface ScreenState {

        data object Loading : ScreenState
        data object Error : ScreenState
        data class Success(val data: List<PopularFilmsVO>) : ScreenState
    }
}