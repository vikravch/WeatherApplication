package com.vikravch.weather.data

import android.content.Context
import android.content.SharedPreferences
import com.vikravch.weather.data.remote.WeatherAPI
import com.vikravch.weather.data.repository.WeatherDataRepository
import com.vikravch.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataComponent {

    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("weather", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherAPI,
        sharedPreferences: SharedPreferences
        ): WeatherRepository {
        return WeatherDataRepository(sharedPreferences, api)
    }

}