package com.dev.feature_popular_films.presentation

import android.app.Application
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dev.common.viewmodels.MviScreenVM
import com.dev.feature_popular_films.di.PopularFilmsComponentProvider
import com.dev.feature_popular_films_connector.PopularFilmsConnector
import com.dev.shared_models.popular_films.PopularFilmsVO
import kotlinx.coroutines.launch

class PopularFilmsVM(
    state: PopularFilmsContract.State,
    private val dataConnector: PopularFilmsConnector
) : MviScreenVM<PopularFilmsContract.Event, PopularFilmsContract.Effect, PopularFilmsContract.State>(
    state
) {

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = dataConnector.getPopularFilms()
            if (data.isNotEmpty()) {
                handleSuccessResult(data.requireNoNulls())
            } else {
                handleErrorResult()
            }
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

    companion object : MavericksViewModelFactory<PopularFilmsVM, PopularFilmsContract.State> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: PopularFilmsContract.State
        ): PopularFilmsVM {
            val component =
                (viewModelContext.app<Application>() as PopularFilmsComponentProvider).getPopularFilmsConnectorIml()
            val dataConnector = component.getPopularFilmsDataConnectorImpl()
            return PopularFilmsVM(
                state = state,
                dataConnector = dataConnector
            )
        }

        override fun initialState(viewModelContext: ViewModelContext): PopularFilmsContract.State {
            return PopularFilmsContract.State(
                uiState = PopularFilmsContract.ScreenState.Loading
            )
        }
    }
}