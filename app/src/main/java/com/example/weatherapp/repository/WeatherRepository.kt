package com.example.weatherapp.repository

import com.example.weatherapp.model.Forecast
import com.example.weatherapp.network.Resource
import com.example.weatherapp.network.ResponseHandler
import com.example.weatherapp.network.WeatherApi
import java.lang.Exception

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val responseHandler: ResponseHandler
){
    suspend fun getCurrentWeather(location: String): Resource<Forecast>{
        return try {
            val response = weatherApi.getForecast(location, "metric")
            return responseHandler.handleSuccess(response)
        } catch (e: Exception){
            responseHandler.handleException(e)
        }
    }
}