package com.example.weatherapp.ui.mainScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.YakutskCity
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.repository.getWeatherRepository
import com.example.weatherapp.ui.mainScreen.recyclerview.models.DailyForecast
import com.example.weatherapp.ui.mainScreen.recyclerview.models.HourlyForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel : ViewModel() {
    //live data, only second variable is accessible outside of viewModel which is immutable
    private val _temperature = MutableLiveData<Double>()
    val temperature: LiveData<Double>
        get() = _temperature

    private val _locality = MutableLiveData<String>()
    val locality: LiveData<String>
        get() = _locality

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _humidity = MutableLiveData<Int>()
    val humidity: LiveData<Int>
        get() = _humidity

    private val _ultraViolet = MutableLiveData<String>()
    val ultraViolet: LiveData<String>
        get() = _ultraViolet

    private val _wind = MutableLiveData<Double>()
    val wind: LiveData<Double>
        get() = _wind

    private val _weatherImageResource = MutableLiveData<String>()
    val weatherImageResource: LiveData<String>
        get() = _weatherImageResource

    private val _hourlyForecastData = MutableLiveData<ArrayList<HourlyForecast>>()
    val hourlyForecastData: LiveData<ArrayList<HourlyForecast>>
        get() = _hourlyForecastData

    private val _dailyForecastData = MutableLiveData<ArrayList<DailyForecast>>()
    val dailyForecastData: LiveData<ArrayList<DailyForecast>>
        get() = _dailyForecastData

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
        _locality.value = response.city.name

        val currentWeather = response.list[time]

        _temperature.value = currentWeather.main.temp
        _description.value = currentWeather.weather[0].main
        _humidity.value = currentWeather.main.humidity
        _ultraViolet.value = "normal"
        _wind.value = currentWeather.wind.speed

        _weatherImageResource.value = currentWeather.weather[0].icon


    }

}