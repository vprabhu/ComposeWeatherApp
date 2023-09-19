package com.vpdevs.minimalisticweatherapp.model

import com.google.gson.annotations.SerializedName


data class Alerts (

  @SerializedName("alert" ) var alert : ArrayList<String> = arrayListOf()

)