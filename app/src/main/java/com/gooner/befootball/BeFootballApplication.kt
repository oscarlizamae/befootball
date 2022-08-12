package com.gooner.befootball

import android.app.Application
import com.gooner.befootball.di.presentationModule
import com.gooner.data.di.networkModule
import com.gooner.data.di.repositoryModule
import com.gooner.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeFootballApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BeFootballApplication)
            modules(
                listOf(presentationModule) +
                listOf(networkModule, repositoryModule) +
                listOf(useCasesModule)
            )
        }
    }
}