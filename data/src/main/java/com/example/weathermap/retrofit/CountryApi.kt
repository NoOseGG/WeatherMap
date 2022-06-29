package com.example.weathermap.retrofit

import com.example.weathermap.model.CountryDTO
import retrofit2.http.GET

interface CountryApi {

    @GET("all")
    suspend fun countries(): List<CountryDTO>
}