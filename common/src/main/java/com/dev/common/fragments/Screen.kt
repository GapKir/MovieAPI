package com.dev.common.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView
import com.dev.common.mvi.MviScreenEffect
import com.dev.common.mvi.MviScreenEvent
import com.dev.common.mvi.MviScreenState
import com.dev.common.viewmodels.MviScreenVM
import kotlinx.coroutines.launch

abstract class Screen: Fragment(), MavericksView {

    protected abstract val binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSideEffects()
    }

    abstract fun handleSideEffects()
}

fun <VM : MviScreenVM<Event, Effect, State>, Event : MviScreenEvent, Effect : MviScreenEffect, State : MviScreenState> Screen.withEffect(
    viewModel: VM,
    block: (Effect) -> Unit
) = viewLifecycleOwner.lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
        viewModel.effectFlow.collect { block(it) }
    }
}