package com.dev.common

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import dagger.MapKey
import kotlin.reflect.KClass

interface AssistedViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState> {
    fun create(state: S): VM
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey(val value: KClass<out MavericksViewModel<*>>)