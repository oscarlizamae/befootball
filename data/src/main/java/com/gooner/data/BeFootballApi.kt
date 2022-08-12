package com.gooner.data

import com.gooner.data.remote.dto.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BeFootballApi {

    companion object {
        const val API_KEY = "A7d1mbjCXrmshPOb29OVdhU2gFFip1RpmMwjsnqt98Fp6mwDrW"
        const val API_HOST = "api-football-v1.p.rapidapi.com"
    }

    @GET("leagues")
    @Headers(
        "X-RapidAPI-Key: A7d1mbjCXrmshPOb29OVdhU2gFFip1RpmMwjsnqt98Fp6mwDrW",
        "X-RapidAPI-Host: api-football-v1.p.rapidapi.com"
    )
    suspend fun getCurrentLeagues(
        @Query("current") current: Boolean = true,
        @Query("season") season: String = ""
    ) : LeagueResponse

}