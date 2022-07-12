package com.tista.feature.movie

import com.tista.feature.movie.data.movieDataModule
import com.tista.feature.movie.domain.movieDomainModule
import com.tista.feature.movie.presentation.moviesPresentationModule
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