package com.example.weather.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass


class WeatherNetworkManager {
    private val baseUrl = "http://api.openweathermap.org/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T: Any> getServiceApi(t: KClass<T>): T = retrofit.create(t.java)
}