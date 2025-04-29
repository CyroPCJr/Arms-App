package com.example.armsapp.di

import android.content.Context
import com.example.armsapp.data.local.db.ArmsDatabase
import com.example.armsapp.data.local.repositories.ArmsLocalRepository
import com.example.armsapp.data.local.repositories.ArmsTeamRepositoryImpl
import com.example.armsapp.data.local.repositories.ProjectRepositoryImpl
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.ArmsApiClientProvider
import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.network.service.ApiService
import com.example.armsapp.network.service.ArmsTeamServiceImpl
import com.example.armsapp.network.service.ProjectServiceImpl
import io.ktor.client.HttpClient

interface DependencyContainer {
    val projectRepository: ArmsLocalRepository<Project, ProjectDto>
    val armsTeamRepository: ArmsLocalRepository<ArmsTeam, ArmsTeamDto>
    val projectService: ApiService<ProjectDto>
    val armsTeamService: ApiService<ArmsTeamDto>
}

class ArmsAppContainer(
    context: Context,
    private val dbProvider: () -> ArmsDatabase = { ArmsDatabase.getDatabase(context.applicationContext) },
    private val clientProvider: () -> HttpClient = { ArmsApiClientProvider.client }
) : DependencyContainer {

    private val database: ArmsDatabase by lazy { dbProvider() }
    private val httpClient: HttpClient by lazy { clientProvider() }

    override val projectRepository: ArmsLocalRepository<Project, ProjectDto> by lazy {
        ProjectRepositoryImpl(dao = database.projectDao())
    }

    override val armsTeamRepository: ArmsLocalRepository<ArmsTeam, ArmsTeamDto> by lazy {
        ArmsTeamRepositoryImpl(dao = database.armsTeamDao())
    }

    override val projectService: ApiService<ProjectDto> by lazy {
        ProjectServiceImpl(client = httpClient)
    }

    override val armsTeamService: ApiService<ArmsTeamDto> by lazy {
        ArmsTeamServiceImpl(client = httpClient)
    }

    companion object {
        @Volatile
        private var instance: ArmsAppContainer? = null

        fun getInstance(context: Context): ArmsAppContainer {
            return instance ?: synchronized(this) {
                instance ?: ArmsAppContainer(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }

}
