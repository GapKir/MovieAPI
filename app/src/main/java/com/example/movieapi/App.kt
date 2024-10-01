package com.example.movieapi

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.dev.popular_films.di.PopularFilmsComponentProvider
import com.dev.popular_films.di.PopularFilmsDependencies
import com.example.movieapi.di.FeatureComponent

class App: Application(), PopularFilmsComponentProvider {

    private lateinit var featureComponent: FeatureComponent

    override fun onCreate() {
        super.onCreate()
        featureComponent = FeatureComponent()
        Mavericks.initialize(this)
    }

    override fun getPopularFilmsConnectorIml(): PopularFilmsDependencies {
        return featureComponent
    }

}