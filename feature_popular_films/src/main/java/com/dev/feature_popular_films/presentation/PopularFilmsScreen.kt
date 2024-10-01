package com.dev.feature_popular_films.presentation

import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.dev.common.fragments.Screen
import com.dev.common.fragments.bindToViewLifecycle
import com.dev.common.fragments.viewBinding
import com.dev.common.fragments.withEffect
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding

class PopularFilmsScreen : Screen() {

    override val binding by viewBinding<FragmentPopularFilmsBinding>()

    private val choreograph by bindToViewLifecycle {
        PopularFilmsChoreograph(
            binding = binding
        )
    }

    private val viewModel by fragmentViewModel(
        viewModelClass = PopularFilmsVM::class
    )

    override fun handleSideEffects() {
        withEffect(viewModel, choreograph::handleEffect)
    }


    override fun invalidate() = withState(viewModel, choreograph::invalidateState)
}