package com.dev.popular_films.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dev.fragments.Screen
import com.dev.fragments.bindToViewLifecycle
import com.dev.fragments.viewBinding
import com.dev.popular_films.databinding.FragmentPopularFilmsBinding
import com.dev.popular_films.di.PopularFilmsComponentProvider
import com.dev.popular_films_connector.PopularFilmsConnector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularFilmsScreen : Screen() {

    override val binding by viewBinding<FragmentPopularFilmsBinding>()
    private lateinit var dataConnector: PopularFilmsConnector

    private val choreograph by bindToViewLifecycle {
        PopularFilmsChoreograph(
            binding = binding
        )
    }

    override fun onAttach(context: Context) {
        val component = (context.applicationContext as PopularFilmsComponentProvider).getPopularFilmsConnectorIml()
        dataConnector = component.getPopularFilmsDataConnectorImpl()
        super.onAttach(context)
    }

    private val viewModel by viewModels<PopularFilmsVM>{PopularFilmsVM.PopularFilmsVMFactory(dataConnector = dataConnector)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner){
            lifecycleScope.launch {
                repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED){
                    viewModel.uiState.collectLatest { state ->
                        choreograph.invalidateState(state)
                    }
                }
            }
        }
    }
}