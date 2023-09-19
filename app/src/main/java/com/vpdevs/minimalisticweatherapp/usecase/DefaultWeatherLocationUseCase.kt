package com.vpdevs.minimalisticweatherapp.usecase

import com.vpdevs.minimalisticweatherapp.model.WeatherRequest
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse
import com.vpdevs.minimalisticweatherapp.repository.WeatherApiRepository

class DefaultWeatherLocationUseCase constructor(
    private val repository: WeatherApiRepository
) : WeatherBasedOnLocationUseCase {

    override val tag: String = "WLB"

    override suspend fun execute(input: WeatherRequest): Result<WeatherResponse> {
        return repository.getWeatherBasedOnLocation(input)
    }

}
