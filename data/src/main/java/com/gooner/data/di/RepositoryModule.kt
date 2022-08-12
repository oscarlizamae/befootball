package com.gooner.data.di

import com.gooner.data.remote.mapper.LeagueDtoMapper
import com.gooner.data.repository.LeagueRepositoryImpl
import com.gooner.domain.repository.LeagueRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LeagueRepository> {
        LeagueRepositoryImpl(get(), get())
    }
    factory { LeagueDtoMapper() }
}