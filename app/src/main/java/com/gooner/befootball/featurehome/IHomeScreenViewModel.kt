package com.gooner.befootball.featurehome

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.League

abstract class IHomeScreenViewModel: ViewModel() {
    abstract val leagues: MutableState<List<League>>
    abstract val liveMatches: MutableState<List<Fixture>>
    abstract fun fetchLivesMatches()
}