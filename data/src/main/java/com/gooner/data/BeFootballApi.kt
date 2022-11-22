package com.gooner.data

import com.gooner.data.remote.dto.FixtureResponse
import com.gooner.data.remote.dto.FixtureResponseInfo
import com.gooner.data.remote.dto.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BeFootballApi {

    // Leagues
    @GET("leagues")
    suspend fun getCurrentLeagues(
        @Query("current") current: Boolean = true,
        @Query("season") season: String = ""
    ) : LeagueResponse

    @GET("fixtures")
    suspend fun getLiveFixtures(
        @Query("live") live: String = "all"
    ) : FixtureResponse

    @GET("fixtures")
    suspend fun getFixtureDetails(
        @Query("id") fixtureId: String
    ) : FixtureResponse

}