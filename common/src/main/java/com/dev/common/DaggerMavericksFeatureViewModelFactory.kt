package com.dev.common

import androidx.activity.ComponentActivity
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext

inline fun <reified VM : MavericksViewModel<S>, S : MavericksState> daggerMavericksFeatureViewModelFactory() =
    DaggerMavericksFeatureViewModelFactory(VM::class.java)


class DaggerMavericksFeatureViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState>(
    private val viewModelClass: Class<VM>
) : MavericksViewModelFactory<VM, S> {

    override fun create(viewModelContext: ViewModelContext, state: S): VM {
        return createViewModel(viewModelContext.activity, state)
    }

    private fun <VM : MavericksViewModel<S>, S : MavericksState> createViewModel(
        fragmentActivity: ComponentActivity,
        state: S
    ): VM {
        val component = findFeatureScopedDIComponent(fragmentActivity)

        val viewModelFactoryMap = component.viewModelFactories()
        val viewModelFactory = viewModelFactoryMap[viewModelClass]

        val castedViewModelFactory = viewModelFactory as? AssistedViewModelFactory<VM, S>
        val viewModel = castedViewModelFactory?.create(state)
        return viewModel as VM
    }

    private fun findFeatureScopedDIComponent(
        activity: ComponentActivity,
    ): MavericksComponent {
        return activity.componentByViewModelClass(viewModelClass)
    }
}
