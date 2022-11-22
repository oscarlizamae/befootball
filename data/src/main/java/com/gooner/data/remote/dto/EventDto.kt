package com.gooner.data.remote.dto

import com.gooner.domain.model.Assist

data class EventDto(
    val type: String? = "",
    val detail: String? = "",
    val player: PlayerDto = PlayerDto(),
    val assist: Assist = Assist(),
    val team: TeamDto,
    val time: EventTimeDto
)

data class EventTimeDto(
    val elapsed: Int? = 0,
    val extra: Int? = 0
)

data class Assist(
    val id: Int? = 0,
    val name: String? = ""
)