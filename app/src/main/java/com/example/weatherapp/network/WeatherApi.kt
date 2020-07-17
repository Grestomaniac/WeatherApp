package com.example.weatherapp.network

import com.example.weatherapp.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getForecast(@Query("q")location: String,
                            @Query("units") unit: String): Forecast
}

