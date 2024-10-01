package com.dev.popular_films.presentation

import android.widget.Toast
import androidx.core.view.isVisible
import com.dev.popular_films.R.string
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding
import com.dev.shared_models.popular_films.PopularFilmsVO

class PopularFilmsChoreograph(
    private val binding: FragmentPopularFilmsBinding,
) {

    private val adapter = MovieAdapter()

    init {
        initView()
    }

    fun invalidateState(state: PopularFilmsContract.ScreenState) {
        when (state) {
            is PopularFilmsContract.ScreenState.Loading -> handleLoadingState()
            is PopularFilmsContract.ScreenState.Error -> handleErrorState()
            is PopularFilmsContract.ScreenState.Success -> handleSuccessState(state.data)
        }
    }

    private fun handleSuccessState(data: List<PopularFilmsVO>) {
        binding.progressBar.isVisible = false
        adapter.submitList(data)
    }

    private fun handleErrorState() {
        binding.progressBar.isVisible = false
        val ctx = binding.root.context
        Toast.makeText(ctx,ctx.getString(string.error), Toast.LENGTH_SHORT).show()
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