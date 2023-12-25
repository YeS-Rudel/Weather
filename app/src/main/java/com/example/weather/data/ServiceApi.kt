package com.example.weather.data

import android.util.Log
import com.example.weather.data.converters.WeatherConvertor
import com.example.weather.domain.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ServiceApi {
    @GET("data/2.5/weather")
    fun weather(@Query("q") q: String, @Query("appid") appid: String): Call<WeatherEntity>
}

interface WeatherServiceApi {
    fun getWeather(city: String, complition: (Result<WeatherEntity>)-> Unit)
}
class WeatherServiceApiInpl: WeatherServiceApi{
    private val baseUrl = "http://api.openweathermap.org/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val serviceApi = retrofit.create(ServiceApi::class.java)
    override fun getWeather(city: String, complition: (Result<WeatherEntity>) -> Unit) {
        serviceApi.weather(city, API_KEY).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                val body = response.body()
                if (body != null) {
                    complition(Result.success(body))
                }
                Log.d("WeatherLog", "Response: ${response.body()}")
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                complition(Result.failure(t))
                Log.d("WeatherLog", "Retrofit error: ${t.message}")
            }
        })
    }
}

interface WeatherService {
    fun getWeather(city: String, complition: (WeatherModel)-> Unit)
}

class  WeatherServiceInpl(private val serviceApi: WeatherServiceApi): WeatherService {
    override fun getWeather(city: String, complition: (WeatherModel) -> Unit) {
        serviceApi.getWeather(city) { result ->
            when {
                result.isSuccess ->
                    complition(WeatherConvertor().convertEntityToModel(result.getOrThrow()))
                result.isFailure ->
                    Log.d("WeatherLog", "WeatherService error: ${result.exceptionOrNull()?.message}")
            }
        }
    }
}