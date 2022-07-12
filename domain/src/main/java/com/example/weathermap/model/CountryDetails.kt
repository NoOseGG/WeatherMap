package com.example.weathermap.model

data class CountryDetails(
    val name: String,
    val latlng: List<Double>,
    val continents: String,
    val population: Long,
    val borders: List<String>,
    val isLandLocked: Boolean,
    val flag: String
)