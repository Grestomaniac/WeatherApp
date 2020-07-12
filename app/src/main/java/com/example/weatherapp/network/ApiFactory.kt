package com.example.weatherapp.network

import com.example.weatherapp.WEATHER_BASE_URL

object ApiFactory{

    val weatherHolderApi: WeatherApi = RetrofitFactory.retrofit(WEATHER_BASE_URL)
        .create(WeatherApi::class.java)
}