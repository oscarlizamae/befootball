package com.gooner.data.util

import com.gooner.data.BuildConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    addTimeout(client)
    client.addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", BuildConfig.API_FOOTBALL_KEY)
            .addHeader("X-RapidAPI-Host", BuildConfig.API_FOOTBALL_HOST)
            .build()
        chain.proceed(request)
    }.build()
    return client.build()
}

private fun addTimeout(clientBuilder: OkHttpClient.Builder) {
    clientBuilder
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
}