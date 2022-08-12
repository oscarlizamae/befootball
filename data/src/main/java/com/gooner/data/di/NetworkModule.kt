package com.gooner.data.di

import com.gooner.data.BeFootballApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"

val networkModule = module {
    single { GsonConverterFactory.create() }
    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
    single {
        get<Retrofit>().create(BeFootballApi::class.java)
    }
}