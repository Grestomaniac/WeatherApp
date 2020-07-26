package com.example.weatherapp.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.model.Coord
import com.example.weatherapp.model.Day

@Entity(tableName = "cities_forecast_table")
data class CityForecast(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,

    @Embedded
    val coord: Coord,

    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int

) {
    @TypeConverters(DayTypeConverters::class)
    var list: List<Day>? = null
}