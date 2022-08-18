package com.gooner.data.di

import com.gooner.data.remote.mapper.*
import com.gooner.data.repository.FixtureRepositoryImpl
import com.gooner.data.repository.LeagueRepositoryImpl
import com.gooner.domain.repository.FixtureRepository
import com.gooner.domain.repository.LeagueRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LeagueRepository> {
        LeagueRepositoryImpl(get(), get())
    }
    single<FixtureRepository> {
        FixtureRepositoryImpl(get(), get())
    }
    factory { LeagueDtoMapper() }
    factory { TeamDtoMapper() }
    factory { PlayerDtoMapper() }
    factory { EventDtoMapper(get(), get()) }
    factory { FixtureDtoMapper(get(), get(), get()) }
}