package com.example.armsapp.ui.screens.weare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armsapp.data.local.repositories.OfflineArmsRepo
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class WeAreScreenViewModel(private val offLineArmsRepo: OfflineArmsRepo<ArmsTeam>) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ArmsTeam>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ArmsTeam>>> = _uiState.asStateFlow()

    init {
        refreshIfNeeded()
    }

    fun refreshIfNeeded() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = offLineArmsRepo.fetchAndSaveArmsRepo()

            result.fold(
                onSuccess = {
                    val localArmsTeam = offLineArmsRepo.getArmsRepo()
                        .distinctUntilChanged()
                        .firstOrNull() ?: emptyList()

                    _uiState.value = UiState.Success(localArmsTeam)
                },
                onFailure = { e ->
                    _uiState.value = UiState.Error(e.message ?: "Erro ao carregar lista do time")
                }
            )
        }
    }
}