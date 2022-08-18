package com.gooner.data.remote.dto

data class EventDto(
    val type: String? = "",
    val detail: String? = "",
    val player: PlayerDto = PlayerDto(),
    val team: TeamDto,
    val time: EventTimeDto
)

data class EventTimeDto(
    val elapsed: Int? = 0,
    val extra: Int? = 0
)