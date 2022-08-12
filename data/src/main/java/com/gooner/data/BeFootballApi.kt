package com.gooner.data

import com.gooner.data.remote.dto.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BeFootballApi {
    
    @GET("leagues")
    suspend fun getCurrentLeagues(
        @Query("current") current: Boolean = true,
        @Query("season") season: String = ""
    ) : LeagueResponse

}