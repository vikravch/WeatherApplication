package com.vikravch.weather.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.vikravch.weather.data.remote.WeatherAPI
import com.vikravch.weather.data.remote.dto.WeatherResponse
import com.vikravch.weather.domain.model.Weather
import com.vikravch.weather.domain.repository.WeatherRepository

class WeatherDataRepository(
    private val sharedPreferences: SharedPreferences,
    private val weatherApi: WeatherAPI
): WeatherRepository {
    override suspend fun getWeatherFromAPI(latitude: Double, longitude: Double): Weather {
        println("getWeatherFromAPI called")
        weatherApi.getWeather(latitude, longitude).body()?.let { weatherResponse ->
            return weatherResponse.toWeather()
        } ?: throw Exception("Weather not found")
    }

    override suspend fun getWeatherFromCache(): Weather? {
        println("getWeatherFromCache called")
        val weatherJson = sharedPreferences.getString("cached_weather", null)
        return if (weatherJson != null) {
            Gson().fromJson(weatherJson, Weather::class.java)
        } else {
            null
        }
    }

    override suspend fun putWeatherToCache(weather: Weather) {
        println("putWeatherToCache called")
        sharedPreferences.edit().putString("cached_weather", Gson().toJson(weather)).apply()
    }

    override suspend fun getLastUpdatedTime(): Long {
        println("getLastUpdatedTime called")
        return sharedPreferences.getLong("last_updated_time", 0)
    }

    override suspend fun setLastUpdatedTime(timeInSeconds: Long) {
        println("setLastUpdatedTime called")
        sharedPreferences.edit().putLong("last_updated_time", timeInSeconds).apply()
    }

}

fun WeatherResponse.toWeather(): Weather {
    return Weather(
        cityName = name,
        temperature = "${main.temp}°F",
        description = weather[0].description,
        windSpeed = "${wind.speed} mph",
        humidity = "${main.humidity}%"
    )
}