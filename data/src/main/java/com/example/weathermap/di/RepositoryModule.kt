package com.example.weathermap.di

import com.example.weathermap.repository.CountryRemoteRepository
import com.example.weathermap.repository.CountryRemoteRepositoryImpl
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
}