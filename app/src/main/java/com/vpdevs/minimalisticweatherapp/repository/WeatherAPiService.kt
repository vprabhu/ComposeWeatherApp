package com.vpdevs.minimalisticweatherapp.repository

import com.vpdevs.minimalisticweatherapp.model.ForecastResponse
import com.vpdevs.minimalisticweatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPiService {

    @GET("v1/current.json")
    suspend fun getWeatherData(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("aqi") aqi: String,
    ) : WeatherResponse

    @GET("v1/forecast.json")
    suspend fun getForecastData(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("alert") alert: String,
        @Query("aqi") aqi: String,
    ) : ForecastResponse
}