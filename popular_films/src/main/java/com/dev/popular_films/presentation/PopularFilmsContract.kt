package com.dev.popular_films.presentation

import com.example.movieapi.model.Movie

object PopularFilmsContract {

    sealed interface Event
    sealed interface Effect

    sealed interface ScreenState {

        data object Loading : ScreenState
        data object Error : ScreenState
        data class Success(val data: List<Movie>) : ScreenState
    }
}