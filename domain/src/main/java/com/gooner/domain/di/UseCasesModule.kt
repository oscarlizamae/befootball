package com.gooner.domain.di

import com.gooner.domain.usecases.GetCurrentLeagues
import com.gooner.domain.usecases.GetCurrentLeaguesImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetCurrentLeagues> { GetCurrentLeaguesImpl(get()) }
}