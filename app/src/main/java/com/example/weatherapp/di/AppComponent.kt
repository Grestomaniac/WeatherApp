package com.example.weatherapp.di

import com.example.weatherapp.ui.mainScreen.MainScreenFragment
import dagger.Component

@Component
interface AppComponent {
    fun inject(fragment: MainScreenFragment)
}