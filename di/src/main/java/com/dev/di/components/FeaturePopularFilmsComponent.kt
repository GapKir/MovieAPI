package com.dev.di.components

import com.airbnb.mvrx.MavericksViewModel
import com.dev.common.AssistedViewModelFactory
import com.dev.common.ViewModelKey
import com.dev.common.MavericksComponent
import com.dev.feature_popular_films.presentation.PopularFilmsVM
import com.dev.feature_popular_films_connector.PopularFilmsConnector
import com.dev.shared_data_connector.PopularFilmsDataConnectorImpl
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FeaturePopularFilmsScope

@FeaturePopularFilmsScope
@Component(
    dependencies = [RepositoryComponent::class],
    modules = [FeaturePopularFilmsModule::class, FeaturePopularFilmsViewModelsModule::class]
)
interface FeaturePopularFilmsComponent: MavericksComponent {

    @Component.Builder
    interface Builder {
        fun repositoryComponent(component: RepositoryComponent): Builder
        fun build(): FeaturePopularFilmsComponent
    }

    override fun viewModelFactories(): Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
    override fun dependentViewModels(): List<Class<out MavericksViewModel<*>>>
}

@Module
interface FeaturePopularFilmsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularFilmsVM::class)
    fun popularFilmsVMFactory(factory: PopularFilmsVM.Factory): AssistedViewModelFactory<*, *>

    @Binds
    fun popularFilmsDataConnector(connector: PopularFilmsDataConnectorImpl): PopularFilmsConnector
}

@Module
class FeaturePopularFilmsViewModelsModule {

    @Provides
    @FeaturePopularFilmsScope
    fun dependentViewModels(): List<Class<out MavericksViewModel<*>>> {
        return listOf(
            PopularFilmsVM::class.java,
        )
    }
}
