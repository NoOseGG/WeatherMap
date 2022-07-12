package com.example.weathermap.mapper

import com.example.weathermap.model.Weather
import com.example.weathermap.model.WeatherDTO

fun WeatherDTO.toWeather(): Weather {
    return Weather(
        country = sys.country,
        temperature = main.temp.toInt()
    )
}