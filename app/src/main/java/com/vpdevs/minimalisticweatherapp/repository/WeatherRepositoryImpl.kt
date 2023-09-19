package com.vpdevs.minimalisticweatherapp.repository

import android.util.Log
import com.vpdevs.minimalisticweatherapp.model.ForecastRequest
import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.model.WeatherRequest
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse

class WeatherRepositoryImpl(
    private val service: WeatherAPiService
) : WeatherApiRepository {

    override suspend fun getWeatherBasedOnLocation(request: WeatherRequest): Result<WeatherResponse> {
        return try {
            val weatherData = service.getWeatherData(
                request.apiKey,
                request.location,
                request.aqi
            )
            Result.success(weatherData)
        } catch (expected: Exception) {
            Result.failure(expected)
        }
    }

    override suspend fun getForecastWeatherData(request: ForecastRequest): Result<ForecastResponse> {
        Log.d("WeatherMainScreen", "WeatherRepositoryImpl -> getForecastWeatherData")
        return try {
            val weatherData = service.getForecastData(
                request.apiKey,
                request.location,
                request.days,
                request.alert,
                request.aqi
            )
            Result.success(weatherData)
        } catch (expected: Exception) {
            Result.failure(expected)
        }
    }
}