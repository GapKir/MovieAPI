package com.example.movieapi

import android.os.Bundle
import android.view.View
import com.dev.common.fragments.Screen
import com.dev.common.fragments.viewBinding
import com.dev.feature_popular_films.FeaturePopularFilms
import com.example.movieapi.databinding.FragmentMainBinding

class MainFragment: Screen() {
    override val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetPopularFilms.setOnClickListener {
            FeaturePopularFilms.launch(
                parentFragmentManager,
                R.id.main_container,
                FeaturePopularFilms.Input
            )
        }
    }

    override fun invalidate() = Unit
}