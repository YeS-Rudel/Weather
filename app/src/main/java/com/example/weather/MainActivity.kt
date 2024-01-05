package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.WeatherFragment
import com.example.weather.data.WeatherRepositoryInpl
import com.example.weather.domain.WeatherInteractor
import com.example.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val getWeather = WeatherInteractor(WeatherRepositoryInpl())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            getWeather(binding.city.text.toString())
        }
    }

    private fun getWeather(city: String) {
        getWeather(city) { weatherModel ->
            Log.d("WeatherLog", "weatherModel: $weatherModel")
        }
    }
}