package com.example.weathermap.di

import com.example.weathermap.repository.CountryRemoteRepository
import com.example.weathermap.usecase.CountryUseCase
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
    fun provideCountriesUseCase(countryRemoteRepository: CountryRemoteRepository): CountryUseCase {
        return CountryUseCase(
            countryRemoteRepository = countryRemoteRepository
        )
    }
}