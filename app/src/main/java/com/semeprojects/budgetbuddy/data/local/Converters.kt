package com.semeprojects.budgetbuddy.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromInstant(instant: Instant?): String? {
        return instant?.let { DateTimeFormatter.ISO_INSTANT.format(it) }
    }

    // String to Instant
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toInstant(value: String?): Instant? {
        return value?.let { Instant.from(DateTimeFormatter.ISO_INSTANT.parse(it)) }
    }
}