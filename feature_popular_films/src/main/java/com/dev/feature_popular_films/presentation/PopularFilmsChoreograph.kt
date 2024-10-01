package com.dev.feature_popular_films.presentation

import android.widget.Toast
import androidx.core.view.isVisible
import com.dev.popular_films.R
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding
import com.dev.shared_models.popular_films.PopularFilmsVO

class PopularFilmsChoreograph(
    private val binding: FragmentPopularFilmsBinding,
) {

    private val adapter = MovieAdapter()

    init {
        initView()
    }

    fun invalidateState(state: PopularFilmsContract.State) {
        when (state.uiState) {
            is PopularFilmsContract.ScreenState.Loading -> handleLoadingState()
            is PopularFilmsContract.ScreenState.Error -> handleErrorState()
            is PopularFilmsContract.ScreenState.Success -> handleSuccessState(state.uiState.data)
        }
    }

    fun handleEffect(effect: PopularFilmsContract.Effect){
        val ctx = binding.root.context
        when(effect){
            PopularFilmsContract.Effect.Error -> Toast.makeText(ctx,ctx.getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSuccessState(data: List<PopularFilmsVO>) {
        binding.progressBar.isVisible = false
        adapter.submitList(data)
    }

    private fun handleErrorState() {
        binding.progressBar.isVisible = false
    }

    private fun handleLoadingState() {
        binding.progressBar.isVisible = true
    }

    private fun initView() {
        with(binding) {
            recycler.apply {
                adapter = this@PopularFilmsChoreograph.adapter
                itemAnimator = null
            }
        }
    }
}