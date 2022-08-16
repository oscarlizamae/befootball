package com.gooner.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamDto(
    val id: Int,
    val name: String,
    @SerializedName("logo")
    val logoUrl: String
)