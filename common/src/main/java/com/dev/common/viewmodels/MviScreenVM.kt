package com.dev.common.viewmodels

import com.airbnb.mvrx.MavericksViewModel
import com.dev.common.mvi.MviScreenEffect
import com.dev.common.mvi.MviScreenEvent
import com.dev.common.mvi.MviScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class MviScreenVM<Event : MviScreenEvent, Effect : MviScreenEffect, State : MviScreenState>(
    state: State
) : MavericksViewModel<State>(state) {

    val effectFlow = MutableSharedFlow<Effect>()

    protected fun setEffect(block:()-> Effect){
        val effectValue = block()
        viewModelScope.launch { effectFlow.emit(effectValue) }
    }
}