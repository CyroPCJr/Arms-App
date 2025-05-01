package com.example.armsapp.ui.screens.weare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armsapp.data.local.repositories.OfflineArmsRepo
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.ui.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WeAreScreenViewModel(offLineArmsRepo: OfflineArmsRepo<ArmsTeam>) : ViewModel() {

    val armsTeamUiState = offLineArmsRepo
        .getArmsRepo()
        .distinctUntilChanged()
        .map<_, UiState<List<ArmsTeam>>> { listProject -> UiState.Success(listProject) }
        .catch { emit(UiState.Error(it.message ?: "Erro ao carregar!")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

}