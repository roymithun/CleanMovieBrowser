package com.tista.cleanmoviebrowser.feature.movie.data

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tista.cleanmoviebrowser.feature.movie.data.database.DATA_BASE_NAME
import com.tista.cleanmoviebrowser.feature.movie.data.database.MovieDatabase
import com.tista.cleanmoviebrowser.feature.movie.data.network.service.MovieRetrofitService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDataModule = module {
    single { provideGson() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, DATA_BASE_NAME
        ).build()
    }
    single { get<MovieDatabase>().movies() }
    single { DefaultMoviesRepository(get(), get()) }
    single { providesMovieRetrofitService(get()) }
}

internal fun providesMovieRetrofitService(retrofit: Retrofit): MovieRetrofitService {
    return retrofit.create(MovieRetrofitService::class.java)
}

internal fun provideGson(): Gson {
    return GsonBuilder().setPrettyPrinting().create()
}