package com.example.weathermap.mapper

import com.example.weathermap.model.Weather
import com.example.weathermap.model.WeatherDTO

fun WeatherDTO.toWeather(): Weather {
    return Weather(
        country = sys.country,
        weather = weather.first().main,
        temperature = main.temp.toInt(),
        icon = weather.first().icon
    )
}