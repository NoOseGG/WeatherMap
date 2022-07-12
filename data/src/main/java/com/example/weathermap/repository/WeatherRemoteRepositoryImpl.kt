package com.example.weathermap.repository

import com.example.weathermap.model.Weather
import com.example.weathermap.mapper.toWeather
import com.example.weathermap.retrofit.WeatherApi
import javax.inject.Inject

class WeatherRemoteRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRemoteRepository {

    override suspend fun weatherCountry(latitude: Double, longitude: Double): Result<Weather> {
        return runCatching {
            api.countryWeather(
                latitude,
                longitude,
                apiKey = "441843f6ae9f894423f8c3d05b1d8979",
                units = "metric"
            ).toWeather()
        }
    }
}