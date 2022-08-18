package com.gooner.befootball.featurelivefixtures

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gooner.domain.model.Fixture
import com.gooner.domain.usecases.GetLiveMatches
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class LiveMatchesViewModel(
    private val getLiveMatches: GetLiveMatches
) : ViewModel() {

    val liveMatches = mutableStateOf<List<Fixture>>(emptyList())

    fun fetchLiveMatches() {
        viewModelScope.launch {
            when (val result = getLiveMatches()) {
                is ResponseResult.Success -> {
                    liveMatches.value = result.data
                }
                else -> {
                    Log.e("Error", result.toString())
                }
            }
        }
    }

}