package com.example.weathermap.usecase

import com.example.weathermap.Country
import com.example.weathermap.repository.CountryRemoteRepository

class CountryUseCase(
    private val countryRemoteRepository: CountryRemoteRepository
) {

    suspend operator fun invoke(): Result<List<Country>> {
        return countryRemoteRepository.countries()
    }
}