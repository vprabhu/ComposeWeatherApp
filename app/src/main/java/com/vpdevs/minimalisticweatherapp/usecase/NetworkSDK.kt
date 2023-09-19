package com.vpdevs.minimalisticweatherapp.usecase

class NetworkSDK {

    fun getRestProvider(): WeatherUseCaseProvider {
        return DefaultWeatherLocationUseCaseProvider()
    }
}