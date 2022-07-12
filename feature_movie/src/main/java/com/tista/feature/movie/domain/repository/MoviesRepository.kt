package com.tista.feature.movie.domain.repository

import com.tista.feature.movie.domain.model.Movie
import com.tista.shared.result.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularTvShows(): Flow<com.tista.shared.result.Result<List<Movie>?>>

    suspend fun getDetails(movieId: Long): com.tista.shared.result.Result<Movie?>
}