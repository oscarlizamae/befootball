package com.gooner.domain.model

data class Event(
    val type: String? = "",
    val detail: String? = "",
    val player: Player = Player(),
    val assist: Assist = Assist(),
    val team: Team,
    val time: EventTime
)

data class EventTime(
    val elapsed: Int? = 0,
    val extra: Int? = 0
)

data class Assist(
    val id: Int? = null,
    val name: String? = null
)