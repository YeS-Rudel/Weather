package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.data.WeatherService
import com.example.weather.data.WeatherServiceApiInpl
import com.example.weather.data.WeatherServiceInpl
import com.example.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val service: WeatherService = WeatherServiceInpl(WeatherServiceApiInpl())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            getWeather("Saratov")
//            getWeather(binding.city.text.toString())
        }
    }

    private fun getWeather(city: String) {
        service.getWeather(city) { weatherModel ->
            Log.d("WeatherLog", "weatherModel: $weatherModel")
        }
    }
}