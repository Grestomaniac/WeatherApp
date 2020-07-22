package com.example.weatherapp.ui.mainScreen.recyclerview

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import kotlin.math.roundToInt

@BindingAdapter("weatherImage")
fun ImageView.setWeatherImage(weatherCode: String) {
    setImageResource(when(weatherCode) {
        "01d", "01n" -> R.drawable.sun_v2
        "02d", "02n" -> R.drawable.cloudy_sunny_solid
        "03d", "03n", "04d", "04n" -> R.drawable.cloudy_solid
        "10d", "09d" -> R.drawable.rainy_solid
        "11d" -> R.drawable.rain_with_thunder_solid

        else -> R.drawable.sun_v2
    })
}

@BindingAdapter("temperatureText")
fun TextView.setTemperature(temperature: Double) {
    text = context.getString(R.string.temperature_format, temperature.roundToInt().toString())
}

@BindingAdapter("windText")
fun TextView.setWindSpeed(windSpeed: Double) {
    text = context.getString(R.string.wind_format, windSpeed.roundToInt().toString())
}

@BindingAdapter("humidityText")
fun TextView.setHumidity(humidity: Int) {
    text = context.getString(R.string.wind_format, humidity.toString())
}
