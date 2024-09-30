package com.dev.popular_films.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PopularFilmsVM: ViewModel() {

    private val _uiState = MutableStateFlow(PopularFilmsContract.ScreenState.Loading)
    val uiState
        get() = _uiState

    init {
        loadData()
    }

    private fun loadData() {

    }
}