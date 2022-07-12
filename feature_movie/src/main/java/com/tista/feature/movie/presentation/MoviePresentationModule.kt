package com.tista.feature.movie.presentation

import com.tista.feature.movie.presentation.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesPresentationModule = module {
    viewModel {
        MovieListViewModel(get(), get())
    }
}