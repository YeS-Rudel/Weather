package com.example.weather.data.api

import com.example.weather.data.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("data/2.5/weather")
    fun weather(@Query("q") q: String, @Query("appid") appid: String): Call<WeatherEntity>
}