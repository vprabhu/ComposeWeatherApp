package com.vpdevs.minimalisticweatherapp.model

import com.google.gson.annotations.SerializedName
import com.vpdevs.minimalisticweatherapp.model.Current
import com.vpdevs.minimalisticweatherapp.model.Location


data class WeatherResponse (

  @SerializedName("location" ) var location : Location? = Location(),
  @SerializedName("current"  ) var current  : Current?  = Current()

)