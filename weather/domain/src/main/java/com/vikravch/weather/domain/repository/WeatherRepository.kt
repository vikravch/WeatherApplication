package com.vikravch.weather.domain.repository

import com.vikravch.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherFromAPI(latitude: Double, longitude: Double): Weather
    suspend fun getWeatherFromCache(): Weather?
    suspend fun putWeatherToCache(weather: Weather)
    suspend fun getLastUpdatedTime(): Long
    suspend fun setLastUpdatedTime(timeInSeconds: Long)
}