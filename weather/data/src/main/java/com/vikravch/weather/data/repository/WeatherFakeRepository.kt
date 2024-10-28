package com.vikravch.weather.data.repository

import com.vikravch.weather.domain.model.Weather
import com.vikravch.weather.domain.model.weather
import com.vikravch.weather.domain.repository.WeatherRepository

class WeatherFakeRepository : WeatherRepository {
    private var lastUpdatedTime = 0L
    private var localWeatherFromCache: Weather? = null

    override suspend fun getWeatherFromAPI(latitude: Double, longitude: Double): Weather {
        println("Fetching weather from API")
        return weather
    }

    override suspend fun getWeatherFromCache(): Weather? {
        println("Fetching weather from cache")
        return localWeatherFromCache
    }

    override suspend fun putWeatherToCache(weather: Weather) {
        println("Putting weather to cache")
        localWeatherFromCache = weather
    }

    override suspend fun getLastUpdatedTime(): Long {
        println("Getting last updated time")
        return lastUpdatedTime
    }

    override suspend fun setLastUpdatedTime(timeInSeconds: Long) {
        println("Setting last updated time")
        lastUpdatedTime = timeInSeconds
    }
}
