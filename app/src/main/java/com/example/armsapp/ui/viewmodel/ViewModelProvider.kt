package com.example.armsapp.ui.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.armsapp.ArmsApplication
import com.example.armsapp.data.local.repositories.OffLineArmsRepository
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.ui.screens.home.HomeScreenViewModel
import com.example.armsapp.ui.screens.weare.WeAreScreenViewModel
import com.example.armsapp.ui.screens.wedo.WeDoScreenViewModel
import com.example.armsapp.ui.screens.wespeak.WeSpeakScreenViewModel
import com.example.armsapp.utils.AndroidLogger

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            MainScreenViewModel(
                offlineProject = OffLineArmsRepository<Project, ProjectDto>(
                    localDataSource = armsApp().container.projectRepository,
                    remoteDataSource = armsApp().container.projectService
                ),
                offlineArms = OffLineArmsRepository<ArmsTeam, ArmsTeamDto>(
                    localDataSource = armsApp().container.armsTeamRepository,
                    remoteDataSource = armsApp().container.armsTeamService
                )
            )
        }

        initializer {
            HomeScreenViewModel(
                offLineArmsRepo = OffLineArmsRepository<Project, ProjectDto>(
                    localDataSource = armsApp().container.projectRepository,
                    remoteDataSource = armsApp().container.projectService
                )
            )
        }

        initializer {
            PlayerViewModel(logger = AndroidLogger())
        }

        initializer {
            WeSpeakScreenViewModel(
                offLineArmsRepo = OffLineArmsRepository<Project, ProjectDto>(
                    localDataSource = armsApp().container.projectRepository,
                    remoteDataSource = armsApp().container.projectService
                )
            )
        }

        initializer {
            WeDoScreenViewModel(
                offLineArmsRepo = OffLineArmsRepository<Project, ProjectDto>(
                    localDataSource = armsApp().container.projectRepository,
                    remoteDataSource = armsApp().container.projectService
                )
            )
        }

        initializer {
            WeAreScreenViewModel(
                offLineArmsRepo = OffLineArmsRepository<ArmsTeam, ArmsTeamDto>(
                    localDataSource = armsApp().container.armsTeamRepository,
                    remoteDataSource = armsApp().container.armsTeamService
                )
            )
        }
    }
}

fun CreationExtras.armsApp(): ArmsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArmsApplication)