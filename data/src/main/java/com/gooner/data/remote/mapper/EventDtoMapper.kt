package com.gooner.data.remote.mapper

import com.gooner.data.remote.dto.EventDto
import com.gooner.data.util.EntityMapper
import com.gooner.domain.model.Assist
import com.gooner.domain.model.Event
import com.gooner.domain.model.EventTime

class EventDtoMapper(
    private val teamDtoMapper: TeamDtoMapper,
    private val playerDtoMapper: PlayerDtoMapper
) : EntityMapper<EventDto, Event> {
    override fun mapFromEntity(entity: EventDto): Event =
        Event(
            type = entity.type,
            detail = entity.detail,
            time = EventTime(
                elapsed = entity.time.elapsed,
                extra = entity.time.extra
            ),
            player = playerDtoMapper.mapFromEntity(entity.player),
            assist = Assist(
                id = entity.assist.id,
                name = entity.assist.name
            ),
            team = teamDtoMapper.mapFromEntity(entity.team)
        )

    /* override fun mapToEntity(model: Event): EventDto {
        TODO("Not yet implemented")
    } */

    override fun mapFromEntityList(entities: List<EventDto>): List<Event> =
        entities.map { mapFromEntity(it) }
}