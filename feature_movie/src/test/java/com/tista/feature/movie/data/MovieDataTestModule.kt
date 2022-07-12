package com.tista.feature.movie.data

import androidx.room.Room
import com.tista.feature.movie.data.database.MovieDatabase
import org.koin.dsl.module

val roomTestModule = module {
    single {
        Room.inMemoryDatabaseBuilder(get(), MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}