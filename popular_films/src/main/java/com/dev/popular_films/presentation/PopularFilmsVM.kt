package com.dev.popular_films.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dev.popular_films_connector.PopularFilmsConnector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularFilmsVM(
    private val dataConnector: PopularFilmsConnector
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<PopularFilmsContract.ScreenState>(PopularFilmsContract.ScreenState.Loading)
    val uiState
        get() = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            dataConnector.getPopularFilms()?.let { data ->
                _uiState.update { PopularFilmsContract.ScreenState.Success(data) }
            } ?: _uiState.update { PopularFilmsContract.ScreenState.Error }
        }
    }

    class PopularFilmsVMFactory(
        private val dataConnector: PopularFilmsConnector
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PopularFilmsVM(dataConnector) as T
        }
    }
}