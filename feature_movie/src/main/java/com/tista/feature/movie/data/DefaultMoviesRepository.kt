package com.tista.feature.movie.data

import com.tista.feature.movie.data.database.MovieDao
import com.tista.feature.movie.data.database.model.toDomainModel
import com.tista.feature.movie.data.network.model.toEntity
import com.tista.feature.movie.data.network.service.MovieRetrofitService
import com.tista.feature.movie.domain.model.Movie
import com.tista.feature.movie.domain.repository.MoviesRepository
import com.tista.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class DefaultMoviesRepository(
    private val moviesRetrofitService: MovieRetrofitService,
    private val movieDao: MovieDao
) :
    MoviesRepository {
    override suspend fun getPopularTvShows(): Flow<com.tista.shared.result.Result<List<Movie>?>> = flow {
        emit(com.tista.shared.result.Result.Loading)
        val moviesListResponse = moviesRetrofitService.getPopularTvShows()
        if (moviesListResponse.isSuccessful && moviesListResponse.body() != null) {
            moviesListResponse.body()?.results?.map {
                it.toEntity()
            }?.let {
                movieDao.insertMovies(it)
            }
        } else {
            emit(com.tista.shared.result.Result.Error(Exception("Could not fetch popular tv shows")))
        }
        movieDao.getAll().map { movieEntity ->
            movieEntity.toDomainModel()
        }.toList().let {
            com.tista.shared.result.Result.Success(it)
        }.let {
            Timber.d("list of movies: $it")
            emit(it)
        }
    }.catch { e ->
        Timber.d("exception $e")
        e.printStackTrace()
        emit(com.tista.shared.result.Result.Error(Exception("Network error! Try again later")))
    }

    override suspend fun getDetails(movieId: Long): com.tista.shared.result.Result<Movie?> =
        com.tista.shared.result.Result.Success(movieDao.getMovie(movieId).toDomainModel())
}