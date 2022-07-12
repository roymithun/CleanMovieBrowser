package com.tista.feature.movie.domain

import com.tista.feature.movie.data.DefaultMoviesRepository
import com.tista.feature.movie.domain.usecase.MovieDetailUseCase
import com.tista.feature.movie.domain.usecase.MoviesListUseCase
import org.koin.dsl.module

val movieDomainModule = module {
    factory { MoviesListUseCase(get<DefaultMoviesRepository>(), get()) }
    factory { MovieDetailUseCase(get<DefaultMoviesRepository>(), get()) }
}