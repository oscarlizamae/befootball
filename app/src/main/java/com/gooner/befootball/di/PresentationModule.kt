package com.gooner.befootball.di

import com.gooner.befootball.featurehome.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeScreenViewModel(get(), get()) }
}