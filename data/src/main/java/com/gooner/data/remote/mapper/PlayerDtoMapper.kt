package com.gooner.data.remote.mapper

import com.gooner.data.remote.dto.PlayerDto
import com.gooner.data.util.EntityMapper
import com.gooner.domain.model.Player

class PlayerDtoMapper : EntityMapper<PlayerDto, Player> {
    override fun mapFromEntity(entity: PlayerDto): Player =
        Player(
            id = entity.id,
            name = entity.name
        )

    /* override fun mapToEntity(model: Player): PlayerDto =
        PlayerDto(
            id = model.id,
            name = model.name
        ) */

    override fun mapFromEntityList(entities: List<PlayerDto>): List<Player> =
        entities.map { mapFromEntity(it) }
}