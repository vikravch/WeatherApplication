package com.vikravch.weather.domain.model

data class Weather(
    val cityName: String,
    val temperature: String,
    val description: String,
    val windSpeed: String,
    val humidity: String
)

val weather = Weather(
    cityName = "Mariupol, Ukraine",
    temperature = "72°F",
    description = "Sunny",
    windSpeed = "5 mph",
    humidity = "10%"
)