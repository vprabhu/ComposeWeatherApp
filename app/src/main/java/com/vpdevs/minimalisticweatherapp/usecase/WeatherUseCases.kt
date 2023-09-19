package com.vpdevs.minimalisticweatherapp.usecase

import com.vpdevs.minimalisticweatherapp.model.ForecastRequest
import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.model.WeatherRequest
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse


interface WeatherBasedOnLocationUseCase : UseCaseSuspended<WeatherRequest, WeatherResponse>

interface ForecastWeatherDataUseCase : UseCaseSuspended<ForecastRequest, ForecastResponse>
