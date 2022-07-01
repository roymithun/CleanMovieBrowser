package com.tista.cleanmoviebrowser.feature.movie.domain.usecase

import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import com.tista.cleanmoviebrowser.feature.movie.domain.repository.MoviesRepository
import com.tista.cleanmoviebrowser.feature.movie.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MoviesListUseCase(
    private val repository: MoviesRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Movie>?>(dispatcher) {
    public override suspend fun execute(parameters: Unit): Flow<Result<List<Movie>?>> {
        return repository.getPopularTvShows()
    }
}