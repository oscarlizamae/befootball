package com.gooner.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LeagueDto(
    val id: Int,
    val name: String,
    val type: String,
    @SerializedName("logo")
    val logoUrl: String
)

data class LeagueResponse(
    val response: List<LeagueInfo>
)

data class LeagueInfo(
    val league: LeagueDto
)