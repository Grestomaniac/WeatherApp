package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.model.Day
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DayTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun convertStringToDayList(data: String?): List<Day>? {
            if (data == null) return emptyList()

            val moshi = Moshi.Builder().build()
            val dataList = Types.newParameterizedType(List::class.java, Day::class.java)
            val jsonAdapter: JsonAdapter<List<Day>> = moshi.adapter(dataList)

            return jsonAdapter.fromJson(data)
        }

        @TypeConverter
        @JvmStatic
        fun convertDayListToJson(list: List<Day>?): String {
            val moshi = Moshi.Builder().build()
            val dataList = Types.newParameterizedType(List::class.java, Day::class.java)
            val jsonAdapter: JsonAdapter<List<Day>> = moshi.adapter(dataList)

            return jsonAdapter.toJson(list)
        }
    }
}