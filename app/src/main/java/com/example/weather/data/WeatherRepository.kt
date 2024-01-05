package com.example.weather.data

import com.example.weather.domain.WeatherModel


interface WeatherRepository {
    fun getWeather(city: String, complition: (Result<WeatherModel>)-> Unit)
}