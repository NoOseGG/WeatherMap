package com.example.weathermap.di

import com.example.weathermap.repository.CountryRemoteRepository
import com.example.weathermap.repository.CountryRemoteRepositoryImpl
import com.example.weathermap.repository.WeatherRemoteRepository
import com.example.weathermap.repository.WeatherRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryRemoteRepository(
        impl: CountryRemoteRepositoryImpl
    ): CountryRemoteRepository

    @Binds
    abstract fun bindWeatherRemoteRepository(
        impl: WeatherRemoteRepositoryImpl
    ): WeatherRemoteRepository
}