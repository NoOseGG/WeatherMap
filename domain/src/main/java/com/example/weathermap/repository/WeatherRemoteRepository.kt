package com.example.weathermap.repository

import com.example.weathermap.model.Weather

interface WeatherRemoteRepository {

    suspend fun weatherCountry(latitude: Double, longitude: Double): Result<Weather>
}