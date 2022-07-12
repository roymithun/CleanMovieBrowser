package com.tista.feature.movie.data.network.model

import com.squareup.moshi.Json
import com.tista.feature.movie.data.database.model.MovieEntity
import java.util.*

data class MovieJson(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    @Json(name = "release_date") val releaseDate: Date?,
    @Json(name = "vote_average") val voteAverage: Float?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?,
)

fun MovieJson.toEntity() = MovieEntity(
    id, imdbId, overview, title, releaseDate, voteAverage, backdropPath, posterPath
)