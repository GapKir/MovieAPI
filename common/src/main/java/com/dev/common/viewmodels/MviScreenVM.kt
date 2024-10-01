package com.dev.common.viewmodels

import com.airbnb.mvrx.MavericksViewModel
import com.dev.common.mvi.MviScreenEffect
import com.dev.common.mvi.MviScreenEvent
import com.dev.common.mvi.MviScreenState

abstract class MviScreenVM<Event : MviScreenEvent, Effect : MviScreenEffect, State : MviScreenState>(state: State) :
    MavericksViewModel<State>(state)