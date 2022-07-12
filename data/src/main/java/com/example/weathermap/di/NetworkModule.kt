package com.example.weathermap.di

import com.example.weathermap.retrofit.CountryApi
import com.example.weathermap.retrofit.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_COUNTRY = "https://restcountries.com/v3.1/"
private const val BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideCountryApi(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COUNTRY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
