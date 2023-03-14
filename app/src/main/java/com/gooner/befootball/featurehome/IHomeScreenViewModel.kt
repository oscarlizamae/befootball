package com.gooner.befootball.featurehome

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.gooner.befootball.featurehome.HomeScreenViewModel.UIState
import com.gooner.befootball.featurehome.HomeScreenViewModel.UIEvent

abstract class IHomeScreenViewModel: ViewModel() {
    abstract val uiState: UIState
    abstract fun onUiEvent(uiEvent: UIEvent)
}