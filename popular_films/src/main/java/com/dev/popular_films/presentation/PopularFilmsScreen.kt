package com.dev.popular_films.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding
import com.example.movieapi.Screen
import com.example.movieapi.bindToViewLifecycle
import com.example.movieapi.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularFilmsScreen : Screen() {

    override val binding by viewBinding<FragmentPopularFilmsBinding>()

    private val viewModel by viewModels<PopularFilmsVM>()

    private val choreograph by bindToViewLifecycle {
        PopularFilmsChoreograph(
            binding = binding
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner){
            lifecycleScope.launch {
                repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED){
                    viewModel.uiState.collectLatest { state ->
                        choreograph.invalidateState(state)
                    }
                }
            }
        }
    }
}