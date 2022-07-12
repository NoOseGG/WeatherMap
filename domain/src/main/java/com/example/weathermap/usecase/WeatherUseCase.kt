package com.example.weathermap.usecase

import com.example.weathermap.model.Weather
import com.example.weathermap.repository.WeatherRemoteRepository

class WeatherUseCase(
    private val repository: WeatherRemoteRepository
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): Result<Weather> {
        return repository.weatherCountry(latitude, longitude)
    }
}