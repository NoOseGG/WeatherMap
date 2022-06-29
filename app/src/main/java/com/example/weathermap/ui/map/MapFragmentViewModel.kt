package com.example.weathermap.ui.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weathermap.model.LceState
import com.example.weathermap.usecase.CountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MapFragmentViewModel @Inject constructor(
    private val countriesUseCase: CountriesUseCase
) : ViewModel() {

    val countriesFlow = flow {
        countriesUseCase.invoke().fold(
            onSuccess = {
                emit(LceState.Content(it))
            },
            onFailure = {
                emit(LceState.Error(it))
            }
        )
    }
}