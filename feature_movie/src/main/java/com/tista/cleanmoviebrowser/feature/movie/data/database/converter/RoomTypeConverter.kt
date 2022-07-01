package com.tista.cleanmoviebrowser.feature.movie.data.database.converter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject
import java.util.*

@TypeConverters
class RoomTypeConverter {
    private val gson: Gson by inject(Gson::class.java)

    @TypeConverter
    fun fromDateToString(date: Date): String {
        return gson.toJson(date, Date::class.java)
    }

    @TypeConverter
    fun fromStringToDate(date: String): Date {
        return gson.fromJson(date, Date::class.java)
    }
}