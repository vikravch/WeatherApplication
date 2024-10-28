package com.vikravch.weather.presentation

import com.vikravch.weather.data.repository.WeatherFakeRepository
import com.vikravch.weather.domain.repository.WeatherRepository
import com.vikravch.weather.domain.use_case.WeatherUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val weatherRepository: WeatherRepository = WeatherFakeRepository()

    val weatherUseCase = WeatherUseCase(weatherRepository)
    val weather = weatherUseCase(37.7749, -122.4194)
    println("Weather after first request: $weather")

    val weather2 = weatherUseCase(37.7749, -122.4194)
    println("Weather after second request: $weather2")

    delay(4000)
    val weather3 = weatherUseCase(37.7749, -122.4194)
    println("Weather after third request: $weather3")
}

/*suspend fun getNewWeather(context: Context): Weather {
    val weatherRepository: WeatherRepository = WeatherDataRepository(
        sharedPreferences = context.getSharedPreferences("weather", Context.MODE_PRIVATE),
        weatherApi = retrofitApi
    )
    val weatherUseCase = WeatherUseCase(weatherRepository)
    val weather = weatherUseCase(37.7749, -122.4194)
    println("Weather: $weather")
    return weather
}*/


