package com.dev.di.components

import com.dev.shared_api.popular_films.PopularFilmsApi
import com.dev.shared_repo.popular_films.PopularFilmsRepository
import com.dev.shared_repo.popular_films.PopularFilmsRepositoryImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class RepositoryScope

@RepositoryScope
@Component(
    dependencies = [NetworkComponent::class],
    modules = [RepositoryModule::class]
)
interface RepositoryComponent {

    @Component.Builder
    interface Builder {
        fun networkComponent(component: NetworkComponent): Builder
        fun build(): RepositoryComponent
    }

    fun popularFilmsRepository(): PopularFilmsRepository

}

@Module
class RepositoryModule {

    @Provides
    fun popularFilmsRepository(api: PopularFilmsApi): PopularFilmsRepository {
        return PopularFilmsRepositoryImpl(api)
    }

}