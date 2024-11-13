package com.dev.feature_popular_films.presentation

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dev.common.AssistedViewModelFactory
import com.dev.common.daggerMavericksFeatureViewModelFactory
import com.dev.common.viewmodels.MviScreenVM
import com.dev.feature_popular_films_connector.PopularFilmsConnector
import com.dev.shared_models.popular_films.PopularFilmsVO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PopularFilmsVM @AssistedInject constructor(
    @Assisted state: PopularFilmsContract.State,
    private val dataConnector: PopularFilmsConnector
) : MviScreenVM<PopularFilmsContract.Event, PopularFilmsContract.Effect, PopularFilmsContract.State>(
    state
) {

    init {
        loadData()
    }

    private fun loadData() {
        handleLoadingResult()
        viewModelScope.launch {
            val data = dataConnector.getPopularFilms()
            if (data.isNotEmpty()) {
                handleSuccessResult(data.requireNoNulls())
            } else {
                handleErrorResult()
            }
        }
    }

    override fun handleEvent(event: PopularFilmsContract.Event) {
        when (event) {
            PopularFilmsContract.Event.Retry -> loadData()
        }
    }

    private fun handleLoadingResult() {
        setState {
            copy(
                uiState = PopularFilmsContract.ScreenState.Loading
            )
        }
    }

    private fun handleSuccessResult(data: List<PopularFilmsVO>) {
        setState {
            copy(
                uiState = PopularFilmsContract.ScreenState.Success(data)
            )
        }
    }

    private fun handleErrorResult() {
        setEffect { PopularFilmsContract.Effect.Error }
        setState {
            copy(
                uiState = PopularFilmsContract.ScreenState.Error
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PopularFilmsVM, PopularFilmsContract.State> {
        override fun create(state: PopularFilmsContract.State): PopularFilmsVM
    }

    companion object : MavericksViewModelFactory<PopularFilmsVM, PopularFilmsContract.State> by daggerMavericksFeatureViewModelFactory() {

        override fun initialState(viewModelContext: ViewModelContext): PopularFilmsContract.State {
            return PopularFilmsContract.State(
                uiState = PopularFilmsContract.ScreenState.Loading
            )
        }
    }
}