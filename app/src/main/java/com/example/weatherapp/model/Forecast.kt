package com.example.weatherapp.model

import com.example.weatherapp.ui.mainScreen.recyclerview.models.DailyForecast
import com.example.weatherapp.ui.mainScreen.recyclerview.models.HourlyForecast

data class Forecast(
    val imageResource: Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Day>,
    val message: Int
) {
    fun getHourlyForecast(): ArrayList<HourlyForecast> {
        val hourlyForecast = ArrayList<HourlyForecast>()
        for (i in 1 until 8) {
            list[i].apply {
                hourlyForecast.add(
                    HourlyForecast(weather[0].icon, dt_txt, main.temp, i)) }

        }
        return hourlyForecast
    }

    fun getDailyForecast(): ArrayList<DailyForecast> {
        val dailyForecast = ArrayList<DailyForecast>()
        for (i in 0 until 7) {
            list[i*4].apply {
                dailyForecast.add(
                    DailyForecast(weather[0].icon, dt_txt, main.temp_min, main.temp_max, i)) }

        }
        return dailyForecast
    }
}