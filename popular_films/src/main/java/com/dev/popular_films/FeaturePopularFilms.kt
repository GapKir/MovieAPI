package com.dev.popular_films

import com.example.movieapi.navigation.NavigationContract

object FeaturePopularFilms:  NavigationContract<FeaturePopularFilms.Input, FeaturePopularFilms.Close>{

    object Input : NavigationContract.Input

    object Close : NavigationContract.Output
}