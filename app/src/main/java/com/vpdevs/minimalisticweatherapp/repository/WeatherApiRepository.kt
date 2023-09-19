package com.vpdevs.minimalisticweatherapp.repository

import com.vpdevs.minimalisticweatherapp.model.ForecastRequest
import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.model.WeatherRequest
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse
import com.vpdevs.network_library.RetrofitClient
import com.vpdevs.vpdevsnetwork.utils.Constants

interface WeatherApiRepository {

    suspend fun getWeatherBasedOnLocation(request: WeatherRequest): Result<WeatherResponse>
    suspend fun getForecastWeatherData(request: ForecastRequest): Result<ForecastResponse>

    class Builder {

        fun build(): WeatherApiRepository {
            val weatherAPiService =
                RetrofitClient.getRetrofitClient(Constants.BASE_URL)
                    .create(WeatherAPiService::class.java)
            return WeatherRepositoryImpl(
                weatherAPiService
            )
        }
    }
}