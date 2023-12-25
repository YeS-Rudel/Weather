package com.example.weather.data

data class WeatherEntity(
    val name: String,
    val main: MainEntity
)

data class MainEntity(
    val temp: Double,
    val pressure: Int,
    val humidity: Int
)