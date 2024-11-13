package com.dev.common

import android.app.Activity
import com.airbnb.mvrx.MavericksViewModel

interface MavericksComponent {
    fun viewModelFactories(): Map<Class<out MavericksViewModel<*>>, AssistedViewModelFactory<*, *>>
    fun dependentViewModels(): List<Class<out MavericksViewModel<*>>>
}

fun Activity.componentByViewModelClass(clazz: Class<*>): MavericksComponent {
    return (application as HasComponents).getComponentByViewModelClass(clazz)
}
