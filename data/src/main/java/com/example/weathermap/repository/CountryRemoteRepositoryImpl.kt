package com.example.weathermap.repository

import com.example.weathermap.Country
import com.example.weathermap.mapper.toCountry
import com.example.weathermap.retrofit.CountryApi
import javax.inject.Inject

class CountryRemoteRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRemoteRepository {

    override suspend fun countries(): Result<List<Country>> {
        return runCatching {
            api.countries().toCountry()
        }
    }
}