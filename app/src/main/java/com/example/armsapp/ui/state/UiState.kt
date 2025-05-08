package com.example.armsapp.ui.state

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    data class Success<T>(
        val data: T,
    ) : UiState<T>()

    data class Error(
        val message: String,
    ) : UiState<Nothing>()
}

sealed interface AppInitState {
    object Loading : AppInitState

    object Success : AppInitState

    data class Error(
        val message: String,
    ) : AppInitState
}
