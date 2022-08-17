package com.gooner.befootball.featurehome

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.League
import com.gooner.domain.usecases.GetLiveMatches
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getLiveMatches: GetLiveMatches
) : ViewModel() {

    @SuppressLint("MutableCollectionMutableState")
    val leagues = mutableStateOf<List<League>>(mutableListOf())
    val liveMatches = mutableStateOf<List<Fixture>>(emptyList())

    fun fetchLivesMatches() {
        viewModelScope.launch {
            when (val result = getLiveMatches()) {
                is ResponseResult.Success -> {
                    liveMatches.value = result.data
                    filterLeagues(liveMatches.value)
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
            leagues.value = it.distinct().toList()
        }
    }

}