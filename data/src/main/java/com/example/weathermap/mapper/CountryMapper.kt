package com.example.weathermap.mapper

import android.util.Log
import com.example.weathermap.Country
import com.example.weathermap.model.CountryDTO
import com.example.weathermap.model.CountryDetails

fun CountryDTO.toCountry(): Country {
    return Country(
        name = name.common ?: "",
        latlng = capitalInfo.latlng ?: emptyList()
    )
}

/*fun CountryDTO.toCountryDetails(): CountryDetails {
    return CountryDetails(
        name = name.common ?: "",
        latlng = capitalInfo.latlng ?: emptyList(),
        continents = continents.first(),
        flag = flags.pgn
    )
}*/

fun List<CountryDTO>.toCountry() : List<Country> {
    return this.map {
        it.toCountry()
    }
}
