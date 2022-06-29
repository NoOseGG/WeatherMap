package com.example.weathermap.model

sealed class LceState<T> {

    data class Content<T>(val value: T): LceState<T>()

    data class Error(val throwable: Throwable): LceState<Nothing>()
}