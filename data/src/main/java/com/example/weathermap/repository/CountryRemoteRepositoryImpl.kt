package com.example.weathermap.repository

import com.example.weathermap.Country
import com.example.weathermap.mapper.toCountry
import com.example.weathermap.mapper.toCountryDetails
import com.example.weathermap.model.CountryDetails
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

    override suspend fun country(countryName: String): Result<CountryDetails> {
        return runCatching {
            api.country(countryName).first().toCountryDetails()
        }
    }
}