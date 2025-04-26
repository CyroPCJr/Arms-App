package com.example.armsapp.ui.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.armsapp.ArmsApplication
import com.example.armsapp.data.local.repositories.OffLineArmsRepository
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.ui.screens.home.HomeScreenViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(
                offLineArmsRepo = OffLineArmsRepository<Project, ProjectDto>(
                    localDataSource = armsApp().container.projectRepository,
                    remoteDataSource = armsApp().container.projectService
                )
            )
        }

        initializer {
            PlayerViewModel()
        }

//        initializer {
//            TeamScreenViewModel(
//                offLineArmsRepo = OfflineArmsTeamRepository(
//                    localDataSource = armsApp().container.armsTeamRepository,
//                    remoteDataSource = armsApp().container.armsTeamService
//                )
//            )
//        }
    }
}

fun CreationExtras.armsApp(): ArmsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArmsApplication)