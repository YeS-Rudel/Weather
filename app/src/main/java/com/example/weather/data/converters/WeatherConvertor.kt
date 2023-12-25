package com.example.weather.data.converters

import com.example.weather.core.Convertor
import com.example.weather.data.WeatherEntity
import com.example.weather.domain.MainModel
import com.example.weather.domain.WeatherModel

class WeatherConvertor: Convertor<WeatherEntity, WeatherModel> {
    override fun convertEntityToModel(entity: WeatherEntity): WeatherModel = with(entity) {
        return WeatherModel(
            name = name,
            main = MainModel(
                temp = main.temp - 273.15,
                pressure = main.pressure,
                humidity = main.humidity
            )
        )
    }

}