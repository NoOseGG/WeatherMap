package com.example.weathermap.model

data class CountryDTO(
    val name: Name,
    val capitalInfo: CapitalInfo,
   /* val continents: List<String>,
    val flags: Flags*/
)

data class Name(
    val common: String?
)

data class CapitalInfo(
    val latlng: List<Double>?
)

/*
data class Flags(
    val pgn: String
)*/
