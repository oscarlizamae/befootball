package com.gooner.data.remote.mapper

import com.gooner.data.remote.dto.LeagueDto
import com.gooner.data.remote.dto.LeagueInfo
import com.gooner.data.util.EntityMapper
import com.gooner.domain.model.League

class LeagueDtoMapper : EntityMapper<LeagueInfo, League> {
    override fun mapFromEntity(entity: LeagueInfo): League =
        League(
            id = entity.league.id,
            name = entity.league.name,
            type = entity.league.type,
            logoUrl = entity.league.logoUrl
        )

    override fun mapToEntity(model: League): LeagueInfo =
        LeagueInfo(
            league = LeagueDto(
                id = model.id,
                name = model.name,
                type = model.type ?: "",
                logoUrl = model.logoUrl ?: ""
            )
        )

    override fun mapFromEntityList(entities: List<LeagueInfo>): List<League> =
        entities.map { mapFromEntity(it) }

}