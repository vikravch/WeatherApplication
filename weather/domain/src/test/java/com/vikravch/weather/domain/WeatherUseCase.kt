package com.vikravch.weather.domain

import com.vikravch.weather.domain.model.weather
import com.vikravch.weather.domain.repository.WeatherRepository
import com.vikravch.weather.domain.use_case.WeatherUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherUseCaseTest {
    private val weatherRepository = mockk<WeatherRepository>()
    private val weatherUseCase = WeatherUseCase(weatherRepository)

    @Test
    fun `get weather for location from api if cache is null`() = runBlocking {
        // given
        val lat = 37.7749
        val lon = -122.4194
        coEvery { weatherRepository.getWeatherFromAPI(any(), any()) } returns weather
        coEvery { weatherRepository.getWeatherFromCache() } returns null
        coEvery { weatherRepository.getLastUpdatedTime()} returns 0L
        coEvery { weatherRepository.setLastUpdatedTime(any()) } returns Unit
        coEvery { weatherRepository.putWeatherToCache(any()) } returns Unit

        // when
        val weather = weatherUseCase(lat, lon)

        // then
        coVerify { weatherRepository.getWeatherFromCache() }
        coVerify { weatherRepository.getWeatherFromAPI(lat, lon) }
        coVerify { weatherRepository.putWeatherToCache(weather) }
        coVerify { weatherRepository.setLastUpdatedTime(any()) }
    }

    @Test
    fun `get weather for location from cache if cache is not null and last updated time is less than 5 minutes`() = runBlocking {
        // given
        val lat = 37.7749
        val lon = -122.4194
        coEvery { weatherRepository.getWeatherFromCache() } returns weather
        coEvery { weatherRepository.getLastUpdatedTime()} returns System.currentTimeMillis()
        coEvery { weatherRepository.getWeatherFromAPI(any(), any()) } returns weather
        coEvery { weatherRepository.putWeatherToCache(any()) } returns Unit
        coEvery { weatherRepository.setLastUpdatedTime(any()) } returns Unit

        // when
        val weather = weatherUseCase(lat, lon)

        // then
        coVerify { weatherRepository.getWeatherFromCache() }
        coVerify(exactly = 0) { weatherRepository.getWeatherFromAPI(lat, lon) }
        coVerify(exactly = 0) { weatherRepository.putWeatherToCache(weather) }
        coVerify(exactly = 0) { weatherRepository.setLastUpdatedTime(any()) }
    }

    @Test
    fun `get weather for location from api if cache is not null and last updated time is more than 5 minutes`() = runBlocking {
        // given
        val lat = 37.7749
        val lon = -122.4194
        coEvery { weatherRepository.getWeatherFromCache() } returns weather
        coEvery { weatherRepository.getLastUpdatedTime()} returns System.currentTimeMillis() - 5 * 60 * 1000
        coEvery { weatherRepository.getWeatherFromAPI(any(), any()) } returns weather
        coEvery { weatherRepository.putWeatherToCache(any()) } returns Unit
        coEvery { weatherRepository.setLastUpdatedTime(any()) } returns Unit

        // when
        val weather = weatherUseCase(lat, lon)

        // then
        coVerify { weatherRepository.getWeatherFromCache() }
        coVerify { weatherRepository.getWeatherFromAPI(lat, lon) }
        coVerify { weatherRepository.putWeatherToCache(weather) }
        coVerify { weatherRepository.setLastUpdatedTime(any()) }
    }
}

