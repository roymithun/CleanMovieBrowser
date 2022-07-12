package com.tista.feature.movie.domain.usecase

import com.tista.feature.movie.domain.model.Movie
import com.tista.feature.movie.domain.repository.MoviesRepository
import com.tista.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MoviesListUseCase(
    private val repository: MoviesRepository,
    dispatcher: CoroutineDispatcher
) : com.tista.shared.usecase.FlowUseCase<Unit, List<Movie>?>(dispatcher) {
    public override suspend fun execute(parameters: Unit): Flow<com.tista.shared.result.Result<List<Movie>?>> {
        return repository.getPopularTvShows()
    }
}