package com.sledz.mobileapp.data.database.daos

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.database.entities.Statistics
import java.lang.reflect.Type


class ListTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun fromStringPrices(data: String?): List<Price> {

        if(data == null) {
            return listOf<Price>()
        }

        val type = object : TypeToken<List<Price>>() {}.type

        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun toStringPrices(data: List<Price>?): String {
        return gson.toJson(data)
    }


    @TypeConverter
    fun fromStringStatistic(data: String?): List<Statistics> {

        if(data == null) {
            return listOf<Statistics>()
        }

        val type = object : TypeToken<List<Price>>() {}.type

        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun toStringStatistics(data: List<Statistics>?): String {
        return gson.toJson(data)
    }

}