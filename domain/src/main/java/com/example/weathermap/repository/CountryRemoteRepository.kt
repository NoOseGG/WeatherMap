package com.example.weathermap.repository

import com.example.weathermap.Country

interface CountryRemoteRepository {

    suspend fun countries(): Result<List<Country>>
}