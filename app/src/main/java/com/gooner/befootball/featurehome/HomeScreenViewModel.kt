package com.gooner.befootball.featurehome

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.League
import com.gooner.domain.usecases.GetLiveMatches
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getLiveMatches: GetLiveMatches
) : IHomeScreenViewModel() {

    @SuppressLint("MutableCollectionMutableState")
    override val leagues = mutableStateOf<List<League>>(mutableListOf())
    override val liveMatches = mutableStateOf<List<Fixture>>(emptyList())

    override fun fetchLivesMatches() {
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