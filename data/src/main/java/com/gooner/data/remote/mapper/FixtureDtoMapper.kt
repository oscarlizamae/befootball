package com.gooner.data.remote.mapper

import com.gooner.data.remote.dto.*
import com.gooner.data.util.EntityMapper
import com.gooner.domain.model.Fixture
import com.gooner.domain.model.FixtureTeams as FixtureTeamsDomain
import com.gooner.domain.model.FixtureStatus as FixtureStatusDomain
import com.gooner.domain.model.Goals

class FixtureDtoMapper(
    private val teamDtoMapper: TeamDtoMapper
) : EntityMapper<FixtureResponseInfo, Fixture> {
    override fun mapFromEntity(entity: FixtureResponseInfo): Fixture =
        Fixture(
            id = entity.fixture.id,
            league = entity.league,
            goals = Goals(home = entity.goals.home, away = entity.goals.away),
            teams = FixtureTeamsDomain(
                home = teamDtoMapper.mapFromEntity(entity.teams.home),
                away = teamDtoMapper.mapFromEntity(entity.teams.away)
            ),
            status = FixtureStatusDomain(
                long = entity.fixture.status.long,
                short = entity.fixture.status.short,
                elapsed = entity.fixture.status.elapsed
            )
        )

    override fun mapToEntity(model: Fixture): FixtureResponseInfo =
        FixtureResponseInfo(
            fixture = FixtureDto(
                id = model.id,
                status = FixtureStatus(
                    long = model.status.long,
                    short = model.status.short,
                    elapsed = model.status.elapsed
                )
            ),
            goals = FixtureGoals(home = model.goals.home, away = model.goals.away),
            teams = FixtureTeams(
                home = teamDtoMapper.mapToEntity(model.teams.home),
                away = teamDtoMapper.mapToEntity(model.teams.away)
            ),
            league = model.league
        )

    override fun mapFromEntityList(entities: List<FixtureResponseInfo>): List<Fixture> =
        entities.map { mapFromEntity(it) }

}