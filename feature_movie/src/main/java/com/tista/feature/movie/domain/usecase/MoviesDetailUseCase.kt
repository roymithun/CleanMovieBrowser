package com.tista.feature.movie.domain.usecase

import com.tista.feature.movie.domain.model.Movie
import com.tista.feature.movie.domain.repository.MoviesRepository
import com.tista.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher

data class MovieDetailUseCaseParams(
    val movieId: Long
)

class MovieDetailUseCase(
    private val repository: MoviesRepository,
    dispatcher: CoroutineDispatcher
) : com.tista.shared.usecase.UseCase<MovieDetailUseCaseParams, com.tista.shared.result.Result<Movie?>>(dispatcher) {
    override suspend fun execute(parameters: MovieDetailUseCaseParams): com.tista.shared.result.Result<Movie?> =
        repository.getDetails(parameters.movieId)
}