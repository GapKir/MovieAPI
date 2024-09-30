package com.dev.popular_films.presentation

import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dev.popular_films.R.string
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding
import com.example.movieapi.model.Movie
import kotlinx.coroutines.launch

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

    private fun handleSuccessState(data: List<Movie>) {
        binding.progressBar.isVisible = false
        adapter.submitList(data)
    }

    private fun handleErrorState() {
        binding.progressBar.isVisible = false
        val ctx = binding.root.context
        Toast.makeText(ctx, getString(ctx, string.error), Toast.LENGTH_SHORT).show()
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