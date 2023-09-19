package com.vpdevs.vpdevsnetwork.utils

import java.text.SimpleDateFormat
import java.util.Date

object Utils {

    fun getDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(System.currentTimeMillis()))
    }

    fun getCurrentHour(): String {
        val formatter = SimpleDateFormat("HH")
        return formatter.format(Date(System.currentTimeMillis()))
    }

}