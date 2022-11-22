package com.gooner.data.remote.mapper

import com.gooner.data.remote.dto.TeamDto
import com.gooner.data.util.EntityMapper
import com.gooner.domain.model.Team

class TeamDtoMapper : EntityMapper<TeamDto, Team> {
    override fun mapFromEntity(entity: TeamDto): Team =
        Team(
            id = entity.id,
            name = entity.name,
            logoUrl = entity.logoUrl
        )

    /* override fun mapToEntity(model: Team): TeamDto =
        TeamDto(
            id = model.id,
            name = model.name,
            logoUrl = model.logoUrl
        ) */

    override fun mapFromEntityList(entities: List<TeamDto>): List<Team> =
        entities.map { mapFromEntity(it) }
}