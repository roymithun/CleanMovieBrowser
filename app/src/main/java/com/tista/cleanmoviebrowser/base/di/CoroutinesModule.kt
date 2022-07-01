package com.tista.cleanmoviebrowser.base.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coroutinesModule = module {
    single { providesIoDispatcher() }
}

fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO