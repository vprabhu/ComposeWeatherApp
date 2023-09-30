package com.vpdevs.minimalisticweatherapp.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpdevs.minimalisticweatherapp.R
import com.vpdevs.minimalisticweatherapp.model.Astro
import com.vpdevs.minimalisticweatherapp.screens.composables.AstroView
import com.vpdevs.minimalisticweatherapp.screens.composables.CurrentWeatherReport
import com.vpdevs.minimalisticweatherapp.screens.composables.DividerView
import com.vpdevs.minimalisticweatherapp.screens.composables.ForecastListView
import com.vpdevs.minimalisticweatherapp.screens.composables.WeatherDetailsView
import com.vpdevs.minimalisticweatherapp.viewmodel.MainViewModel
import com.vpdevs.vpdevsnetwork.utils.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherMainScreen(
    viewModel: MainViewModel = viewModel(),
    onNextButtonClicked: () -> Unit,
    ) {

    val forecastWeatherData = viewModel.forecastWeatherData.collectAsState()

    viewModel.getForecastData(days = 3)
    val forecastData = forecastWeatherData.value.forecast?.forecastday
    val current = forecastWeatherData.value.current
    val date = Utils.getDate()
    Log.d(
        "WeatherMainScreen", "WeatherMainScreen:" +
                " ${forecastWeatherData.value.forecast?.forecastday?.size} | " +
                "hour : ${Utils.getCurrentHour()}"
    )
    var currentHour = 0
    var temp = 0.0
    var placeName = ""
    var weatherStatus = ""
    var weatherImage = ""
    var country = ""
    var astro = Astro()
    if (forecastData?.size!! > 0) {
        temp = forecastData[0].hour[0].tempC!!
        placeName = forecastWeatherData.value.location?.name.toString()
        country = forecastWeatherData.value.location?.region.toString()
        currentHour = forecastWeatherData.value.current
            ?.lastUpdated?.split(" ")
            ?.get(1)?.split(":")?.get(0)?.toInt() ?: 0
        astro = forecastData[0].astro!!
        weatherStatus = forecastData[0].hour[currentHour].condition?.text.toString()
        weatherImage = "https://${forecastData[0].hour[currentHour].condition?.icon.toString()}"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(R.color.black)
            )
            .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),

        ) {
        Text(
            text = "$placeName , $country",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.White
        )
        Text(
            text = date,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp),
            color = Color.White
        )

        CurrentWeatherReport(weatherImage = weatherImage, temp = temp)

        Text(
            text = weatherStatus,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        DividerView()

        if (forecastData.size > 0) {
            ForecastListView(forecastData[0].hour.subList(currentHour, forecastData[0].hour.size))
        }

        DividerView()

        AstroView(astro = astro)

        DividerView()

        current?.let { WeatherDetailsView(current = it) }

        DividerView()

        OutlinedButton(
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(50.dp),
            onClick = { onNextButtonClicked() },
        ) {
            Text(
                text = "Forecast ->",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}
