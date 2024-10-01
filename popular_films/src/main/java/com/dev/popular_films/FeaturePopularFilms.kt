package com.dev.popular_films

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.dev.popular_films.presentation.PopularFilmsScreen
import com.dev.common.navigation.NavigationContract

object FeaturePopularFilms:
    NavigationContract<FeaturePopularFilms.Input, FeaturePopularFilms.Close> {

    object Input : NavigationContract.Input

    object Close : NavigationContract.Output

    fun launch(fragmentManager: FragmentManager,@IdRes containerId: Int, input: NavigationContract.Input) {
        fragmentManager
            .beginTransaction()
            .replace(containerId, PopularFilmsScreen())
            .addToBackStack(null)
            .commit()

    }
}