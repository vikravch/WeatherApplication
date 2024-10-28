package com.vikravch.weather.domain.use_case

import com.vikravch.weather.domain.model.Weather
import com.vikravch.weather.domain.repository.WeatherRepository

val MIN_UPDATED_TIME = 3 * 60 * 1000

class WeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Weather {
        val weatherFromCache = weatherRepository.getWeatherFromCache()
        if (System.currentTimeMillis() - weatherRepository.getLastUpdatedTime() < MIN_UPDATED_TIME && weatherFromCache!=null) {
            return weatherFromCache
        } else {
            val weather = weatherRepository.getWeatherFromAPI(latitude, longitude)
            weatherRepository.putWeatherToCache(weather)
            weatherRepository.setLastUpdatedTime(System.currentTimeMillis())
            return weather
        }
    }
}