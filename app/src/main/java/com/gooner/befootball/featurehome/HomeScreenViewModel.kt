package com.gooner.befootball.featurehome

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gooner.domain.model.League
import com.gooner.domain.usecases.GetCurrentLeagues
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getCurrentLeagues: GetCurrentLeagues
) : ViewModel() {

    val leagues = mutableStateOf<List<League>>(emptyList())

    fun fetchCurrentLeagues() {
        viewModelScope.launch {
            when(val result = getCurrentLeagues()) {
                is ResponseResult.Success -> {
                    leagues.value = result.data
                    Log.d("LeaguesResult", leagues.value.toString())
                }
                else -> {
                    Log.e("HomeScreen", result.toString())
                }
            }
        }
    }

}