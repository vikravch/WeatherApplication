package com.vikravch.weather.domain

import com.vikravch.weather.domain.repository.WeatherRepository
import com.vikravch.weather.domain.use_case.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainComponent {

    @Provides
    @Singleton
    fun provideWeatherUseCase(
        weatherRepository: WeatherRepository
    ): WeatherUseCase {
        return WeatherUseCase(weatherRepository)
    }
}