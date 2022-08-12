package com.gooner.data

import com.gooner.data.remote.dto.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BeFootballApi {

    companion object {
        const val API_KEY = BuildConfig.API_FOOTBALL_KEY
        const val API_HOST = BuildConfig.API_FOOTBALL_HOST
    }

    @GET("leagues")
    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    suspend fun getCurrentLeagues(
        @Query("current") current: Boolean = true,
        @Query("season") season: String = ""
    ) : LeagueResponse

}