package com.example.weatherapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.weatherapp.database.CityForecast
import com.example.weatherapp.database.ForecastDatabase
import com.example.weatherapp.database.ForecastDatabaseDao
import com.example.weatherapp.model.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var forecastDatabaseDao: ForecastDatabaseDao
    private lateinit var forecastDatabase: ForecastDatabase

    @Before
    fun createDataBase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        forecastDatabase = Room.inMemoryDatabaseBuilder(context, ForecastDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        forecastDatabaseDao = forecastDatabase.forecastDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDataBase() {
        forecastDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetObject() {
        val forecastObject = CityForecast(0,
        "DefaultCity",
        "DefaultCountry",
        Coord(.0, .0),
        0,
        1,
        1,
        2
        /*listOf(Day(
            Clouds(1),
            0,
            "00:00",
            Main(.0, 1, 1, 1, 1, .0, .0, .0, .0),
            Rain(.0),
            Snow(.0),
            Sys(""),
            emptyList(),
            Wind(0, .0)
        ))*/)

        forecastDatabaseDao.insert(forecastObject)

        val forecastFromDataBase = forecastDatabaseDao.get(0)
        assertEquals(forecastFromDataBase.name, "DefaultCity")

        val inexistentObject = forecastDatabaseDao.get(1)
        assertNull(inexistentObject)
    }

}