package com.example.weathermap.model

data class CountryDTO(
    val name: Name,
    val capitalInfo: CapitalInfo,
    val continents: List<String>,
    val population: Long,
    val borders: List<String>,
    val landlocked: Boolean,
    val capital: List<String>,
    val region: String,
    val flags: Flags
)

data class Name(
    val common: String?
)

data class CapitalInfo(
    val latlng: List<Double>?
)

data class Flags(
    val png: String
)
