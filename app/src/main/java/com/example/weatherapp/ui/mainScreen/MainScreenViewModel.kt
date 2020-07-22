package com.example.weatherapp.ui.mainScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.TemperatureUnit
import com.example.weatherapp.YakutskCity
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.repository.getWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class MainScreenViewModel : ViewModel() {
    //live data, only second variable is accessible outside of viewModel which is immutable
    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String>
        get() = _temperature

    private val _locality = MutableLiveData<String>()
    val locality: LiveData<String>
        get() = _locality

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String>
        get() = _humidity

    private val _ultraViolet = MutableLiveData<String>()
    val ultraViolet: LiveData<String>
        get() = _ultraViolet

    private val _wind = MutableLiveData<String>()
    val wind: LiveData<String>
        get() = _wind

    private val _weatherImageResource = MutableLiveData<Int>()
    val weatherImageResource: LiveData<Int>
        get() = _weatherImageResource

    init {
        loadLocality(YakutskCity)
    }

    fun loadLocality(locality: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getWeatherRepository().getCurrentWeather(locality)
            withContext(Dispatchers.Main) {
                if (response.data == null) Log.d("dsd", "null")
                fillMainData(response.data!!)
            }
        }
    }

    private fun fillMainData(response: Forecast, time: Int = 0) {
        if (response == null) Log.d("abrakadabra", "response is null")
        if (response.cod == null) Log.d("abrakadabra", "list is null")
        if (response.city == null) Log.d("abrakadabra", "city is null")
        if (response.city.name == null) Log.d("abrakadabra", "name is null")

        _locality.value = response.city.name

        val currentWeather = response.list[time]

        _temperature.value = currentWeather.main.temp.roundToInt().toString()
        _description.value = currentWeather.weather[0].main
        _humidity.value = currentWeather.main.humidity.toString()
        _ultraViolet.value = "normal"
        _wind.value = currentWeather.wind.speed.roundToInt().toString()

        _weatherImageResource.value = getWeatherImageResource(currentWeather.weather[0].icon)

        Log.d("WEATHER_RESPONSE", "weather size is ${currentWeather.weather.size}")
    }

    private fun getWeatherImageResource(icon: String): Int {
        return when (icon) {
            "01d", "01n" -> R.drawable.sun_v2
            "02d", "02n" -> R.drawable.cloudy_sunny_solid
            "03d", "03n", "04d", "04n" -> R.drawable.cloudy_solid
            "10d", "09d" -> R.drawable.rainy_solid
            "11d" -> R.drawable.rain_with_thunder_solid

            else -> R.drawable.sun_v2
        }
    }

}