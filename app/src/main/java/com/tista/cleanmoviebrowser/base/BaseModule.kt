package com.tista.cleanmoviebrowser.base

import com.tista.cleanmoviebrowser.base.presentation.navigation.NavManager
import org.koin.dsl.module

val baseModule = module {
    single { NavManager() }
}