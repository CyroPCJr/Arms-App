package com.example.armsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armsapp.data.local.repositories.OffLineArmsRepository
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.ui.state.AppInitState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val offlineProject: OffLineArmsRepository<Project, ProjectDto>,
    private val offlineArms: OffLineArmsRepository<ArmsTeam, ArmsTeamDto>
) : ViewModel() {

    private val _appInitState = MutableStateFlow<AppInitState>(AppInitState.Loading)
    val appInitState: StateFlow<AppInitState> = _appInitState.asStateFlow()

    init {
        consumeRestApi()
    }

    private fun consumeRestApi() {
        viewModelScope.launch {
            _appInitState.value = AppInitState.Loading

            try {
                awaitAll(
                    async { offlineProject.fetchAndSaveArmsRepo() },
                    async { offlineArms.fetchAndSaveArmsRepo() }
                )
                _appInitState.value = AppInitState.Success

            } catch (e: Exception) {
                _appInitState.value = AppInitState.Error(
                    e.localizedMessage ?: "Erro ao carregar dados"
                )
            }
        }
    }

    fun retryInit() {
        consumeRestApi()
    }
}