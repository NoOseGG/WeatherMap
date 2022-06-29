package com.example.weathermap.ui.countrydetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermap.model.LceState
import com.example.weathermap.usecase.CountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CountryDetailsFragmentViewModel @Inject constructor(
    private val countryUseCase: CountryUseCase
) : ViewModel() {

    val countryName = MutableSharedFlow<String>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val countryFlow = countryName.onEach {
        Log.i("MyTag", "$it")
    }.mapLatest {
        val countryDetails = countryUseCase(it)
        Log.i("MyTag", "$countryDetails")
        countryDetails.fold(
            onSuccess = { country ->
                LceState.Content(country)
            },
            onFailure = { throwable ->
                LceState.Error(throwable)
            }
        )
    }.shareIn(
        viewModelScope,
        SharingStarted.Eagerly,
        replay = 1
    )

    fun sendCountryName(name: String) {
        countryName.tryEmit(name)
    }
}