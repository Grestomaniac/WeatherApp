package com.example.weatherapp.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

    fun onSearchButtonClick() {
    }

    fun loadCity() {

    }

}