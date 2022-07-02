package com.tista.cleanmoviebrowser.feature.movie

import com.tista.cleanmoviebrowser.feature.movie.data.database.model.MovieEntity
import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object TestData {
    val movie1 = Movie(
        id = 335787,
        imdbId = null,
        overview = "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
        title = "Uncharted",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.1F,
        backdropPath = "/aEGiJJP91HsKVTEPy1HhmN0wRLm.jpg",
        posterPath = "/rJHC1RUORuUhtfNb4Npclx0xnOf.jpg"
    )

    val movie2 = Movie(
        id = 453395,
        imdbId = null,
        overview = "Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.",
        title = "Doctor Strange in the Multiverse of Madness",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.5F,
        backdropPath = "/wcKFYIiVDvRURrzglV9kGu7fpfY.jpg",
        posterPath = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg"
    )

    val movie3 = Movie(
        id = 648579,
        imdbId = null,
        overview = "Creatively unfulfilled and facing financial ruin, Nick Cage must accept a $1 million offer to attend the birthday of a dangerous superfan. Things take a wildly unexpected turn when Cage is recruited by a CIA operative and forced to live up to his own legend, channeling his most iconic and beloved on-screen characters in order to save himself and his loved ones.",
        title = "The Unbearable Weight of Massive Talent",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.1F,
        backdropPath = "/m0YjB4VfghKey8Ppsmz8qCd0v1m.jpg",
        posterPath = "/aqhLeieyTpTUKPOfZ3jzo2La0Mq.jpg"
    )

    val movieList = listOf(movie1, movie2, movie3)

    val movieEntity1 = MovieEntity(
        id = 335787,
        imdbId = null,
        overview = "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
        title = "Uncharted",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.1F,
        backdropPath = "/aEGiJJP91HsKVTEPy1HhmN0wRLm.jpg",
        posterPath = "/rJHC1RUORuUhtfNb4Npclx0xnOf.jpg"
    )

    val movieEntity2 = MovieEntity(
        id = 453395,
        imdbId = null,
        overview = "Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.",
        title = "Doctor Strange in the Multiverse of Madness",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.5F,
        backdropPath = "/wcKFYIiVDvRURrzglV9kGu7fpfY.jpg",
        posterPath = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg"
    )

    val movieEntity3 = MovieEntity(
        id = 648579,
        imdbId = null,
        overview = "Creatively unfulfilled and facing financial ruin, Nick Cage must accept a $1 million offer to attend the birthday of a dangerous superfan. Things take a wildly unexpected turn when Cage is recruited by a CIA operative and forced to live up to his own legend, channeling his most iconic and beloved on-screen characters in order to save himself and his loved ones.",
        title = "The Unbearable Weight of Massive Talent",
        releaseDate = Date.from(
            LocalDate.parse("2022-05-04", DateTimeFormatter.ISO_DATE)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        ),
        voteAverage = 7.1F,
        backdropPath = "/m0YjB4VfghKey8Ppsmz8qCd0v1m.jpg",
        posterPath = "/aqhLeieyTpTUKPOfZ3jzo2La0Mq.jpg"
    )

    val movieEntityList = listOf(movieEntity1, movieEntity2, movieEntity3)

}