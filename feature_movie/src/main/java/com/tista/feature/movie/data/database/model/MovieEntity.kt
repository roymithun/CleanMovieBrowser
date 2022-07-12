package com.tista.feature.movie.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tista.feature.movie.domain.model.Movie
import java.util.*

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    val releaseDate: Date?,
    val voteAverage: Float?,
    val backdropPath: String?,
    val posterPath: String?,
)

internal fun MovieEntity.toDomainModel() = Movie(
    id, imdbId, overview, title, releaseDate, voteAverage, backdropPath, posterPath
)