package com.vpdevs.minimalisticweatherapp.model

data class WeatherRequest(

    val apiKey: String = "",
    val location: String = "",
    val aqi: String = ""
)
