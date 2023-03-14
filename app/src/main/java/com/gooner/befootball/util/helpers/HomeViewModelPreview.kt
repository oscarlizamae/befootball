package com.gooner.befootball.util.helpers

import com.gooner.befootball.featurehome.HomeScreenViewModel
import com.gooner.befootball.featurehome.IHomeScreenViewModel

class HomeViewModelPreview(
    override val uiState: HomeScreenViewModel.UIState
) : IHomeScreenViewModel() {
    override fun onUiEvent(uiEvent: HomeScreenViewModel.UIEvent) { }
}