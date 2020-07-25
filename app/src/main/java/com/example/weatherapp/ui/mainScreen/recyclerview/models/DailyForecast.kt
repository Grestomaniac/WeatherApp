package com.example.weatherapp.ui.mainScreen.recyclerview.models

data class DailyForecast(var weatherIcon: String,
                         var time: String,
                         var minTemperature: Double,
                         var maxTemperature: Double,
                         var dayIndex: Int)