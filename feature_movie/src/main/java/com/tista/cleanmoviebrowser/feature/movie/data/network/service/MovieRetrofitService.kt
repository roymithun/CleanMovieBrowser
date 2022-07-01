package com.tista.cleanmoviebrowser.feature.movie.data.network.service

import com.tista.cleanmoviebrowser.feature.movie.data.network.model.MovieJson
import com.tista.cleanmoviebrowser.feature.movie.data.network.model.PaginatedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofitService {
    @GET("movie/popular")
    suspend fun getPopularTvShows(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): Response<PaginatedListResponse<MovieJson>>


    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Long,
        @Query("language") query: String? = null
    ): Response<MovieJson>
}