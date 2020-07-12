package com.example.weatherapp.model

data class Forecast(
    val imageResource: Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Day>,
    val message: Int
)