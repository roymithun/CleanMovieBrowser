package com.tista.cleanmoviebrowser.feature.movie.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tista.cleanmoviebrowser.feature.movie.data.database.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movies where id = :movieId")
    suspend fun getMovie(movieId: Long): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)
}