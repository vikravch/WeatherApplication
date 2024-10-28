package com.vikravch.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikravch.weather.domain.model.Weather
import com.vikravch.weather.domain.use_case.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    var weather by mutableStateOf<Weather?>(null)
        private set

    fun refreshWeather() {
        viewModelScope.launch {
            val weatherResponse = weatherUseCase(37.7749, -122.4194)
            println("Weather: $weatherResponse")
            weather = weatherResponse
        }
    }
}