package com.example.weathermap.retrofit

import com.example.weathermap.model.CountryDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("all")
    suspend fun countries(): List<CountryDTO>

    @GET("name/{countryName}")
    suspend fun country(
        @Path("countryName") countryName: String
    ): List<CountryDTO>
}