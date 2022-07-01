package com.tista.cleanmoviebrowser.feature.movie.domain.usecase

import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import com.tista.cleanmoviebrowser.feature.movie.domain.repository.MoviesRepository
import com.tista.cleanmoviebrowser.feature.movie.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher

data class MovieDetailUseCaseParams(
    val movieId: Long
)

class MovieDetailUseCase(
    private val repository: MoviesRepository,
    dispatcher: CoroutineDispatcher
) : UseCase<MovieDetailUseCaseParams, Result<Movie?>>(dispatcher) {
    override suspend fun execute(parameters: MovieDetailUseCaseParams): Result<Movie?> =
        repository.getDetails(parameters.movieId)
}