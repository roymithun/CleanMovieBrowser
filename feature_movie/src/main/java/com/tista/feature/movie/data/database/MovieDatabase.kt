package com.tista.feature.movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tista.feature.movie.data.database.converter.RoomTypeConverter
import com.tista.feature.movie.data.database.model.MovieEntity

const val DATA_BASE_NAME = "movies_db"

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movies(): MovieDao
}