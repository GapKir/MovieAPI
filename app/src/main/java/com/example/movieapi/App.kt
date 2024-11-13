package com.example.movieapi

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.dev.common.HasComponents
import com.dev.di.AppInjector
import com.dev.di.ComponentHolder

class App: Application(), HasComponents by ComponentHolder() {

    override fun onCreate() {
        super.onCreate()
        AppInjector().initDi(this)
        Mavericks.initialize(this)
    }

}