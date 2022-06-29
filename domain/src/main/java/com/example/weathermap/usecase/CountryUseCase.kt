package com.example.weathermap.usecase

import com.example.weathermap.model.CountryDetails
import com.example.weathermap.repository.CountryRemoteRepository

class CountryUseCase(
    private val countryRemoteRepository: CountryRemoteRepository
) {

    suspend operator fun invoke(countryName: String): Result<CountryDetails> {
        return countryRemoteRepository.country(countryName)
    }
}