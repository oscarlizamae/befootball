package com.gooner.befootball.featurefixturedetails

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gooner.domain.model.Fixture
import com.gooner.domain.usecases.GetFixtureDetails
import com.gooner.domain.util.ResponseResult
import kotlinx.coroutines.launch

class FixtureDetailsViewModel(
    private val getFixtureDetails: GetFixtureDetails
) : ViewModel() {

    val fixture: MutableState<Fixture?> = mutableStateOf(null)

    fun fetchFixtureDetails(fixtureId: Int) {
        viewModelScope.launch {
            when (val result = getFixtureDetails(fixtureId)) {
                is ResponseResult.Success -> {
                    Log.d("FixtureDetails", result.data.toString())
                    fixture.value = result.data
                }
                is ResponseResult.Error -> {
                    Log.d("FixtureDetails", result.toString())
                }
                else -> { }
            }
        }
    }

}