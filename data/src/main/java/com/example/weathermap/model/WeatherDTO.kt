package com.example.weathermap.model

data class WeatherDTO(
    val sys: Sys,
    val main: Main
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class InfoWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)