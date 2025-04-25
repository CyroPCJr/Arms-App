package com.example.armsapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armsapp.data.local.repositories.OfflineArmsRepo
import com.example.armsapp.domain.model.Project
import com.example.armsapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val offLineArmsRepo: OfflineArmsRepo<Project>
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Project>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Project>>> = _uiState.asStateFlow()

    init {
        observeLocalData()
        refreshIfNeeded()
    }

    private fun observeLocalData() {
        viewModelScope.launch {
            offLineArmsRepo.getArmsRepo()
                .distinctUntilChanged()
                .collect { localProjects ->
                    _uiState.value = UiState.Success(localProjects)
                }
        }
    }

    private fun refreshIfNeeded() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = offLineArmsRepo.fetchAndSaveArmsRepo()
            result.exceptionOrNull()?.let { e ->
                _uiState.value = UiState.Error(e.message ?: "Failed to fetch projects")
            }
        }
    }
}