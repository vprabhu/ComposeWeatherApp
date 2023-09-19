package com.vpdevs.minimalisticweatherapp.model

data class ForecastRequest(
    val apiKey: String = "",
    val location: String = "",
    val aqi: String = "",
    val days: Int = 0,
    val alert: String = ""
)
