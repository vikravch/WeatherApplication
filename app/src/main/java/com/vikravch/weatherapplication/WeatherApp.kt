package com.vikravch.weatherapplication

import android.app.Application
import androidx.annotation.Keep
import dagger.hilt.android.HiltAndroidApp

@Keep
@HiltAndroidApp
class WeatherApp: Application() {}