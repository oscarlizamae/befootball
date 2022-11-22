package com.gooner.domain.model

data class Fixture(
    val id: Int,
    val referee: String? = "",
    val venue: Venue,
    val league: League,
    val goals: Goals,
    val teams: FixtureTeams,
    val status: FixtureStatus,
    val events: List<Event>
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

data class Venue(
    val id: Int,
    val name: String,
    val city: String
)