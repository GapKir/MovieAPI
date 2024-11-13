package com.dev.di

import com.dev.common.HasComponents
import com.dev.di.components.DaggerFeaturePopularFilmsComponent
import com.dev.di.components.DaggerNetworkComponent
import com.dev.di.components.DaggerRepositoryComponent

class AppInjector {

    fun initDi(componentsHolder: HasComponents) {
        val networkComponent = DaggerNetworkComponent.builder().build()
        componentsHolder.onComponentBuilt(networkComponent)

        val repositoryComponent = DaggerRepositoryComponent
            .builder()
            .networkComponent(networkComponent)
            .build()
        componentsHolder.onComponentBuilt(repositoryComponent)

        val featurePopularFilmsComponent =
            DaggerFeaturePopularFilmsComponent
                .builder()
                .repositoryComponent(repositoryComponent)
                .build()
        componentsHolder.onComponentBuilt(featurePopularFilmsComponent)
    }
}