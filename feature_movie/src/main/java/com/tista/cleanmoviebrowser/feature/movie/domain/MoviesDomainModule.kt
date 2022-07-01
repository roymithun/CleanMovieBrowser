package com.tista.cleanmoviebrowser.feature.movie.domain

import androidx.room.Room
import com.tista.cleanmoviebrowser.feature.movie.data.DefaultMoviesRepository
import com.tista.cleanmoviebrowser.feature.movie.data.database.DATA_BASE_NAME
import com.tista.cleanmoviebrowser.feature.movie.data.database.MovieDatabase
import com.tista.cleanmoviebrowser.feature.movie.domain.usecase.MovieDetailUseCase
import com.tista.cleanmoviebrowser.feature.movie.domain.usecase.MoviesListUseCase
import org.koin.dsl.module

val movieDomainModule = module {
    factory { MoviesListUseCase(get<DefaultMoviesRepository>(), get()) }
    factory { MovieDetailUseCase(get<DefaultMoviesRepository>(), get()) }
}