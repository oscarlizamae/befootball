package com.gooner.data.remote.dto

import com.gooner.domain.model.League

data class FixtureResponse(
    val response: List<FixtureResponseInfo>
)

data class FixtureResponseInfo(
    val fixture: FixtureDto,
    val league: LeagueDto,
    val teams: FixtureTeams,
    val goals: FixtureGoals
)

data class FixtureDto(
    val id: Int,
    val status: FixtureStatus
)

data class FixtureTeams(
    val home: TeamDto,
    val away: TeamDto
)

data class FixtureStatus(
    val long: String,
    val short: String,
    val elapsed: Int
)

data class FixtureGoals(
    val home: Int,
    val away: Int
)