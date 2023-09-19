package com.vpdevs.minimalisticweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpdevs.minimalisticweatherapp.model.ForecastRequest
import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.model.WeatherRequest
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse
import com.vpdevs.minimalisticweatherapp.usecase.NetworkSDK
import com.vpdevs.minimalisticweatherapp.usecase.WeatherUseCaseProvider
import com.vpdevs.vpdevsnetwork.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel(

) : ViewModel() {

    private var weatherUseCaseProvider: WeatherUseCaseProvider

    private val _weatherData = MutableStateFlow(WeatherResponse())
    val weatherData = _weatherData.asStateFlow()

    private val _forecastWeatherData = MutableStateFlow(ForecastResponse())
    val forecastWeatherData = _forecastWeatherData.asStateFlow()

    init {
        weatherUseCaseProvider = NetworkSDK().getRestProvider()
//        getForecastData(days = 3)
    }

    fun getWeatherData(): WeatherResponse {
        var result: WeatherResponse = WeatherResponse()
        viewModelScope.launch(Dispatchers.IO)
        {
            val req = WeatherRequest(
                apiKey = Constants.API_KEY,
                location = "london",
                aqi = "yes"
            )
            weatherUseCaseProvider.getWeatherDataBasedOnLocation()
                .execute(req)
                .onSuccess {
                    _weatherData.value = it
                }
                .onFailure {
                    _weatherData.value = WeatherResponse()
                }
        }

        return result
    }

    fun getForecastData(days: Int): ForecastResponse {
        Log.d("WeatherMainScreen", "getForecastData:")
        var result: ForecastResponse = ForecastResponse()
        viewModelScope.launch(Dispatchers.IO)
        {
            val req = ForecastRequest(
                apiKey = Constants.API_KEY,
                location = "Bengaluru",
                aqi = "yes",
                days = days,
                alert = "yes"
            )
            weatherUseCaseProvider.getForecastData()
                .execute(req)
                .onSuccess {
                    Log.d("WeatherMainScreen", "getForecastData: onSuccess")
                    if (it.forecast?.forecastday?.size!! > 0) {
                        _forecastWeatherData.value = it
                    }
                }
                .onFailure {
                    Log.d("WeatherMainScreen", "getForecastData: onFailure")
                    _forecastWeatherData.value = ForecastResponse()
                }
        }

        return result
    }
}