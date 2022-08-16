package com.gooner.data.util

interface EntityMapper<Entity, T> {
    fun mapFromEntity(entity: Entity): T
    fun mapToEntity(model: T): Entity
    fun mapFromEntityList(entities: List<Entity>): List<T>
}
