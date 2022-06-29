package com.example.weathermap.model

data class CountryDetails(
    val name: String,
    val latlng: List<Double>,
    val continents: String,
    val flag: String
)