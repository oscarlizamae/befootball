package com.gooner.befootball.util.helpers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gooner.befootball.featurehome.IHomeScreenViewModel
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.League

class HomeViewModelPreview(
    override val leagues: MutableState<List<League>> = mutableStateOf(emptyList()),
    override val liveMatches: MutableState<List<Fixture>> = mutableStateOf(emptyList())
) : IHomeScreenViewModel() {
    override fun fetchLivesMatches() { }
}