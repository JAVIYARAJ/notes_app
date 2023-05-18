package com.example.notesapp.database.convetors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class NoteTypeConvertors {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time;
    }

    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }

    @TypeConverter
    fun fromArrayListToString(list: java.util.ArrayList<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToArrayList(list: String): java.util.ArrayList<String> {
        return Gson().fromJson(list, object : TypeToken<ArrayList<String>>() {}.type)
    }
}