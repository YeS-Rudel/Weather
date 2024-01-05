package com.example.weather.domain

import android.util.Log
import com.example.weather.data.WeatherRepository

class  WeatherInteractor(private val serviceApi: WeatherRepository) {
    operator fun invoke(city: String, complition: (WeatherModel) -> Unit) {
        serviceApi.getWeather(city) { result ->
            when {
                result.isSuccess ->
                    complition(result.getOrThrow())
                result.isFailure ->
                    Log.d(
                        "WeatherLog",
                        "WeatherService error: ${result.exceptionOrNull()?.message}"
                    )
            }
        }
    }
}