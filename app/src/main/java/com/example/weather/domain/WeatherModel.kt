package com.example.weather.domain

data class WeatherModel(
    val name: String,
    val main: MainModel
)

data class MainModel(
    val temp: Double,
    val pressure: Int,
    val humidity: Int
)