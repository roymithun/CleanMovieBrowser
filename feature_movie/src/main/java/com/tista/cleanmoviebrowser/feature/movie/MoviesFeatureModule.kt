package com.tista.cleanmoviebrowser.feature.movie

import com.tista.cleanmoviebrowser.feature.movie.data.movieDataModule
import com.tista.cleanmoviebrowser.feature.movie.domain.movieDomainModule
import com.tista.cleanmoviebrowser.feature.movie.presentation.moviesPresentationModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

object MoviesFeatureModule {
    fun init() = loadKoinModules(
        listOf(
            movieDomainModule,
            movieDataModule,
            moviesPresentationModule
        )
    )

    fun deInit() = unloadKoinModules(
        listOf(
            movieDomainModule,
            movieDataModule,
            moviesPresentationModule
        )
    )
}