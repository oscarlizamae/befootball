package com.gooner.domain.model

data class Fixture(
    val id: Int,
    val league: League,
    val goals: Goals,
    val teams: FixtureTeams,
    val status: FixtureStatus
)

data class FixtureTeams(
    val home: Team,
    val away: Team
)

data class Goals(
    val home: Int,
    val away: Int
)

data class FixtureStatus(
    val long: String,
    val short: String,
    val elapsed: Int
)