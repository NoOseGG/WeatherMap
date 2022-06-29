package com.example.weathermap.repository

import com.example.weathermap.Country
import com.example.weathermap.model.CountryDetails

interface CountryRemoteRepository {

    suspend fun countries(): Result<List<Country>>

    suspend fun country(countryName: String): Result<CountryDetails>
}