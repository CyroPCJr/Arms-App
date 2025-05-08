package com.example.armsapp.data.local.repositories

import android.database.SQLException
import com.example.armsapp.data.local.dao.ProjectDao
import com.example.armsapp.data.local.entities.toProject
import com.example.armsapp.domain.model.Project
import com.example.armsapp.network.dto.ProjectDto
import com.example.armsapp.network.dto.toProjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepositoryImpl(
    private val dao: ProjectDao,
) : ArmsLocalRepository<Project, ProjectDto> {
    override fun getAll(): Flow<List<Project>> = dao.getAll().map { projects -> projects.map { it.toProject() } }

    override suspend fun insertAll(remoteList: List<ProjectDto>): Result<Unit> =
        try {
            dao.insertAll(remoteList.map { it.toProjectEntity() })
            Result.success(Unit)
        } catch (e: SQLException) {
            Result.failure<Unit>(e)
        }

    override suspend fun hasData(): Boolean = dao.hasData()
}
