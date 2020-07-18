package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.network.*
import retrofit2.Retrofit
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

fun getWeatherRepository() = WeatherRepository(
    provideWeatherApi(settings()),
    ResponseHandler()
)

private fun settings(): Retrofit {
    return provideRetrofit(
        provideOkHttpClient(
            AuthInterceptor(),
            provideLoggingInterceptor()
        )
    )
}