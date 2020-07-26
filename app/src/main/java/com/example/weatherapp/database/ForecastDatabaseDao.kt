package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ForecastDatabaseDao {
    @Insert
    fun insert(cityForecast: CityForecast)

    @Update
    fun update(cityForecast: CityForecast)

    @Delete
    fun delete(cityForecast: CityForecast)

    @Query("SELECT * from cities_forecast_table WHERE id = :cityId")
    fun get(cityId: Int): CityForecast

    @Query("DELETE FROM cities_forecast_table")
    fun clear()

    @Query("SELECT * FROM cities_forecast_table ORDER BY name")
    fun getAllCitiesForecast(): LiveData<List<CityForecast>>


}