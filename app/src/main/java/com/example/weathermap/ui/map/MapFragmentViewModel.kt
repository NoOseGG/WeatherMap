package com.example.weathermap.ui.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weathermap.model.LceState
import com.example.weathermap.usecase.CountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MapFragmentViewModel @Inject constructor(
    private val countryUseCase: CountryUseCase
) : ViewModel() {

    val countriesFlow = flow {
        countryUseCase.invoke().fold(
            onSuccess = {
                emit(LceState.Content(it))
            },
            onFailure = {
                emit(LceState.Error(it))
            }
        )
    }
}