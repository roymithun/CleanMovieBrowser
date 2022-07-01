package com.tista.cleanmoviebrowser.feature.movie.domain.repository

import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import com.tista.cleanmoviebrowser.feature.movie.data.network.model.PaginatedListResponse
import com.tista.cleanmoviebrowser.feature.movie.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularTvShows(): Flow<Result<List<Movie>?>>

    suspend fun getDetails(movieId: Long): Result<Movie?>
}