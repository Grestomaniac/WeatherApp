package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityForecast::class], version = 1, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {

    abstract val forecastDatabaseDao: ForecastDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        fun getInstance(context: Context): ForecastDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ForecastDatabase::class.java,
                        "forecast_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}