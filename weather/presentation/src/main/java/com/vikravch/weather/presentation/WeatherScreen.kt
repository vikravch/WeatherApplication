package com.vikravch.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.refreshWeather()
    }
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Weather: ${viewModel.weather}")
        Button(onClick = {
            viewModel.refreshWeather()
        }) {
            Text(text = "Refresh")
        }
    }
}