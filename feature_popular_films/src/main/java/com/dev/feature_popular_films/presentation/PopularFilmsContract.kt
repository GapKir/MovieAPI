package com.dev.feature_popular_films.presentation

import com.dev.common.mvi.MviScreenEffect
import com.dev.common.mvi.MviScreenEvent
import com.dev.common.mvi.MviScreenState
import com.dev.shared_models.popular_films.PopularFilmsVO

object PopularFilmsContract {

    sealed interface Event: MviScreenEvent {
        data object Retry: Event
    }
    sealed interface Effect: MviScreenEffect {
        data object Error: Effect
    }

    data class State(
        val uiState: ScreenState
    ): MviScreenState

    sealed class ScreenState {
        data object Loading : ScreenState()
        data object Error : ScreenState()
        data class Success(val data: List<PopularFilmsVO>) : ScreenState()
    }
}