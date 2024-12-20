package com.example.chapapp_compose.core.data

sealed class UiState<out T : Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}