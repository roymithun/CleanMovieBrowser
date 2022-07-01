package com.tista.cleanmoviebrowser.feature.movie.domain.model

import android.os.Parcelable
import android.text.format.DateFormat
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

const val IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500"

@Parcelize
data class Movie(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    val releaseDate: Date?,
    val voteAverage: Float?,
    val backdropPath: String?,
    val posterPath: String?,
) : Parcelable {
    @IgnoredOnParcel
    val posterImageUrl = "$IMAGE_URL_BASE$posterPath"

    @IgnoredOnParcel
    val backdropImageUrl = "$IMAGE_URL_BASE$backdropPath"

    @IgnoredOnParcel
    val formattedReleaseDate = "${DateFormat.format("yyyy", releaseDate)}"
    override fun toString(): String {
        return "Movie(id=$id, imdbId=$imdbId, overview=$overview, title='$title', releaseDate=$releaseDate, voteAverage=$voteAverage, backdropPath=$backdropPath, posterPath=$posterPath, posterImageUrl='$posterImageUrl', backdropImageUrl='$backdropImageUrl', formattedReleaseDate='$formattedReleaseDate')"
    }
}
