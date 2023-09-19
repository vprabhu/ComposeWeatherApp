package com.vpdevs.minimalisticweatherapp.usecase

import com.vpdevs.minimalisticweatherapp.model.ForecastRequest
import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.repository.WeatherApiRepository

class DefaultForecastWeatherUseCase constructor(
    private val repository: WeatherApiRepository
) : ForecastWeatherDataUseCase {
    override suspend fun execute(input: ForecastRequest): Result<ForecastResponse> {
        return repository.getForecastWeatherData(input)
    }

    override val tag: String = "WLB"

}
