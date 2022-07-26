package ru.something.weatherandroidapp.repository

import ru.something.weatherandroidapp.model.Weather
import ru.something.weatherandroidapp.model.getRussianCities
import ru.something.weatherandroidapp.model.getWorldCities

interface MainRepository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}

class MainRepositoryImpl : MainRepository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus(): List<Weather> = getRussianCities()
    override fun getWeatherFromLocalStorageWorld(): List<Weather> = getWorldCities()
}

