package com.example.weathermap.di

import com.example.weathermap.repository.CountryRemoteRepository
import com.example.weathermap.repository.WeatherRemoteRepository
import com.example.weathermap.usecase.CountriesUseCase
import com.example.weathermap.usecase.CountryUseCase
import com.example.weathermap.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCountriesUseCase(countryRemoteRepository: CountryRemoteRepository): CountriesUseCase {
        return CountriesUseCase(
            countryRemoteRepository = countryRemoteRepository
        )
    }

    @Provides
    @Singleton
    fun provideCountryUseCase(countryRemoteRepository: CountryRemoteRepository): CountryUseCase {
        return CountryUseCase(
            countryRemoteRepository = countryRemoteRepository
        )
    }

    @Provides
    @Singleton
    fun provideWeatherUseCase(weatherRemoteRepository: WeatherRemoteRepository): WeatherUseCase {
        return WeatherUseCase(
            repository = weatherRemoteRepository
        )
    }
}