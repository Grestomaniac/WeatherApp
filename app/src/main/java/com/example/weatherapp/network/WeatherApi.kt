package com.example.weatherapp.network

import com.example.weatherapp.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun getWeather(@Query("q") cityName: String, @Query("units") TemperatureUnit: String): Forecast
}