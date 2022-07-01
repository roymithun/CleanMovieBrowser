package com.tista.cleanmoviebrowser.app

import com.facebook.stetho.Stetho
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.tista.cleanmoviebrowser.BuildConfig
import com.tista.cleanmoviebrowser.app.data.network.networkModule
import com.tista.cleanmoviebrowser.base.baseModule
import com.tista.cleanmoviebrowser.base.di.coroutinesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CleanMovieBrowserApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initStetho()
        startKoinInjection()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun startKoinInjection() {
        startKoin {
            androidContext(this@CleanMovieBrowserApplication)
            modules(
                listOf(
                    baseModule,
                    networkModule,
                    coroutinesModule
                )
            )
        }
    }
}