package com.vpdevs.minimalisticweatherapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityScreen() {

    val cityName = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        Text(text = "AddCityScreen")

        TextField(
            label = {
                Text(text = "Enter PlaceName")
            },
            value = cityName.value,
            onValueChange = {
                cityName.value = it
            }
        )

        Button(onClick = {
            Toast.makeText(context, cityName.value, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Add City")
        }

    }


}