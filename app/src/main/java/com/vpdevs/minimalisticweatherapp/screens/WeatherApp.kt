package com.vpdevs.minimalisticweatherapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class WeatherAppScreens() {
    AddCityScreen,
    WeatherScreen,
    ForecastScreen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp() {

    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WeatherAppScreens.AddCityScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = WeatherAppScreens.AddCityScreen.name) {
                AddCityScreen()
            }

            composable(route = WeatherAppScreens.WeatherScreen.name) {
                WeatherMainScreen()
            }

            composable(route = WeatherAppScreens.ForecastScreen.name) {
                ForecastScreen()
            }
        }

    }

}