package com.gooner.domain.di

import com.gooner.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetCurrentLeagues> { GetCurrentLeaguesImpl(get()) }
    factory<GetLiveMatches> { GetLiveMatchesImpl(get()) }
}