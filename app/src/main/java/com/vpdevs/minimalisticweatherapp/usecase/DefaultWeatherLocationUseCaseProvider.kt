package com.vpdevs.minimalisticweatherapp.usecase

import com.vpdevs.minimalisticweatherapp.repository.WeatherApiRepository

class DefaultWeatherLocationUseCaseProvider() :
    WeatherUseCaseProvider {
    private var repository: WeatherApiRepository = WeatherApiRepository.Builder().build()

    override fun getWeatherDataBasedOnLocation(): WeatherBasedOnLocationUseCase {
        return DefaultWeatherLocationUseCase(repository)
    }

    override fun getForecastData(): ForecastWeatherDataUseCase {
        return DefaultForecastWeatherUseCase(repository)
    }
}