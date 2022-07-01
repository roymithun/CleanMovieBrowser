package com.tista.cleanmoviebrowser.feature.movie.data

import com.tista.cleanmoviebrowser.feature.movie.data.database.MovieDao
import com.tista.cleanmoviebrowser.feature.movie.data.database.model.toDomainModel
import com.tista.cleanmoviebrowser.feature.movie.data.network.model.toEntity
import com.tista.cleanmoviebrowser.feature.movie.data.network.service.MovieRetrofitService
import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import com.tista.cleanmoviebrowser.feature.movie.domain.repository.MoviesRepository
import com.tista.cleanmoviebrowser.feature.movie.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class DefaultMoviesRepository(
    private val moviesRetrofitService: MovieRetrofitService,
    private val movieDao: MovieDao
) :
    MoviesRepository {
    override suspend fun getPopularTvShows(): Flow<Result<List<Movie>?>> = flow {
        emit(Result.Loading)
        val moviesListResponse = moviesRetrofitService.getPopularTvShows()
        if (moviesListResponse.isSuccessful && moviesListResponse.body() != null) {
            moviesListResponse.body()?.results?.map {
                it.toEntity()
            }?.let {
                movieDao.insertMovies(it)
            }
        } else {
            emit(Result.Error(Exception("Could not fetch popular tv shows")))
        }
        movieDao.getAll().map { movieEntity ->
            movieEntity.toDomainModel()
        }.toList().let {
            Result.Success(it)
        }.let {
            Timber.d("list of movies: $it")
            emit(it)
        }
    }.catch { e ->
        Timber.d("exception $e")
        e.printStackTrace()
        emit(Result.Error(Exception("Network error! Try again later")))
    }

    override suspend fun getDetails(movieId: Long): Result<Movie?> =
        Result.Success(movieDao.getMovie(movieId).toDomainModel())
}