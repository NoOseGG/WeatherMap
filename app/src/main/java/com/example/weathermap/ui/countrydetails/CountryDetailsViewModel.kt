package com.example.weathermap.ui.countrydetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermap.model.LceState
import com.example.weathermap.usecase.CountryUseCase
import com.example.weathermap.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val countryUseCase: CountryUseCase,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {

    private val args = CountryDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val countryFlow = flow {
        val countryDetails = countryUseCase(args.countryName)
        countryDetails.fold(
            onSuccess = { country ->
                emit(LceState.Content(country))
            },
            onFailure = { throwable ->
                emit(LceState.Error(throwable))
            }
        )
    }.shareIn(
        viewModelScope,
        SharingStarted.Eagerly,
        replay = 1
    )

    val sendCoordinates = MutableSharedFlow<List<Double>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val weatherFlow = sendCoordinates.onEach {

    }.mapLatest {
        val weather = weatherUseCase(it.first(), it.last())
        weather.fold(
            onSuccess = { weather ->
                LceState.Content(weather)
            },
            onFailure = { error ->
                LceState.Error(error)
            }
        )
    }

    fun sendCoordinates(coordinates: List<Double>) {
        sendCoordinates.tryEmit(coordinates)
    }

}