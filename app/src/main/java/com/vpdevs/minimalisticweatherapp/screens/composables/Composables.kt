package com.vpdevs.minimalisticweatherapp.screens.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vpdevs.minimalisticweatherapp.model.Astro
import com.vpdevs.minimalisticweatherapp.model.Current
import com.vpdevs.minimalisticweatherapp.model.Hour

@Composable
fun CurrentWeatherReport(weatherImage: String, temp: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = weatherImage,
            contentDescription = null,
            modifier = Modifier
                .height(64.dp)
                .width(64.dp),
            alignment = Alignment.Center
        )

        Text(
            text = "$temp\u2103",
            fontSize = 64.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp),
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
fun WeatherDetailsView(current: Current) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) {
            when (it) {
                0 -> WeatherDetailsItem(title = "Humidity", data = current.humidity.toString())
                1 -> WeatherDetailsItem(
                    title = "Air Quality",
                    data = current.airQuality?.o3.toString()
                )

                2 -> WeatherDetailsItem(title = "Visibility", data = current.visKm.toString())
                3 -> WeatherDetailsItem(title = "Wind Direction", data = current.windDir.toString())
                4 -> WeatherDetailsItem(title = "Wind Speed", data = current.windKph.toString())
                5 -> WeatherDetailsItem(title = "UV", data = current.uv.toString())
            }
        }
    }
}

@Composable
fun WeatherDetailsItem(title: String, data: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
        )

        Text(
            text = data,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun ForecastListView(hour: MutableList<Hour>) {
    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp , bottom = 16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        items(count = hour.size) {
            ForecastItemView(hour = hour[it])
        }
    }
}


@Composable
private fun ForecastItemView(hour: Hour) {

    Column(
        modifier = Modifier
            .padding(4.dp)
            .border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "${hour.tempC} C",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp, end = 8.dp)
        )
        AsyncImage(
            model = "https://${hour.condition?.icon.toString()}",
            contentDescription = null,
            modifier = Modifier
                .height(48.dp)
                .width(48.dp),
            alignment = Alignment.Center
        )
        Text(
            text = getTime(hour.time),
            fontSize = 10.sp,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )
    }
}

private fun getTime(timeInMillis: String?): String {
    return timeInMillis?.split(" ")?.get(1).toString()
}

@Composable
fun DividerView(){
    Divider(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp , bottom = 8.dp),
        thickness = 1.dp,
        color = Color.White
    )
}

@Composable
fun AstroView(astro: Astro) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp , bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        astro.sunrise?.let {
            WeatherDetailsItem(
                "Sunrise",
                it
            )
        }

        astro.sunset?.let {
            WeatherDetailsItem(
                "Sunset",
                it
            )
        }
    }

}
