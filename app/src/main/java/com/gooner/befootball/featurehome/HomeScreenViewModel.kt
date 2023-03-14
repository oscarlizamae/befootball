package com.gooner.befootball.featurehome

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.League
import com.gooner.domain.usecases.GetLiveMatches
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getLiveMatches: GetLiveMatches,
) : IHomeScreenViewModel() {

    override var uiState by mutableStateOf(UIState())
        private set

    private fun onStart() {
        onCallGetLivesMatches()
    }

    private fun onCallGetLivesMatches() {
        viewModelScope.launch {
            when (val result = getLiveMatches()) {
                is ResponseResult.Success -> {
                    uiState = uiState.copy(
                        liveMatches = result.data
                    )
                    filterLeagues(uiState.liveMatches)
                }
                else -> {
                    Log.e("HomeScreen", result.toString())
                }
            }
        }
    }

    private fun filterLeagues(liveMatches: List<Fixture>) {
        val uniqueLeagues: MutableList<League> = mutableListOf()
        for (fixture in liveMatches) {
            uniqueLeagues.add(fixture.league)
        }
        uniqueLeagues.let {
            uiState = uiState.copy(
                leagues = it.distinct().toList()
            )
        }
    }

    override fun onUiEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            UIEvent.OnStart -> onStart()
            UIEvent.OnCallGetLiveMatches -> onCallGetLivesMatches()
        }
    }

    data class UIState(
        val leagues: List<League> = emptyList(),
        val liveMatches: List<Fixture> = emptyList()
    )

    sealed class UIEvent {
        object OnStart: UIEvent()
        object OnCallGetLiveMatches: UIEvent()
    }

}