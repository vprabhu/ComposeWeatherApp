package com.vpdevs.minimalisticweatherapp.usecase


interface WeatherUseCaseProvider {

    fun getWeatherDataBasedOnLocation(): WeatherBasedOnLocationUseCase

    fun getForecastData(): ForecastWeatherDataUseCase
}