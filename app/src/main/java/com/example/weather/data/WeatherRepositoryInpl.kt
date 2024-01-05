package com.example.weather.data

import android.util.Log
import com.example.weather.core.WeatherNetworkManager
import com.example.weather.data.api.ServiceApi
import com.example.weather.data.converters.WeatherConvertor
import com.example.weather.domain.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryInpl: WeatherRepository {

    private val serviceApi: ServiceApi = WeatherNetworkManager().getServiceApi(ServiceApi::class)
    override fun getWeather(city: String, complition: (Result<WeatherModel>) -> Unit) {
        serviceApi.weather(city, API_KEY).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                val body = response.body()
                if (body != null) {
                    val data = WeatherConvertor().convertEntityToModel(body)
                    complition(Result.success(data))
                }
                Log.d("WeatherLog", "WeatherRepository response: ${response.body()}")
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                complition(Result.failure(t))
                Log.d("WeatherLog", "WeatherRepository error: ${t.message}")
            }
        })
    }
}