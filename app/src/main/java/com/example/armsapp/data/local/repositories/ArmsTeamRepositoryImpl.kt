package com.example.armsapp.data.local.repositories

import android.database.SQLException
import com.example.armsapp.data.local.dao.ArmsTeamDao
import com.example.armsapp.data.local.entities.toArmsTeam
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.network.dto.ArmsTeamDto
import com.example.armsapp.network.dto.toArmsTeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArmsTeamRepositoryImpl(private val dao: ArmsTeamDao) :
    ArmsLocalRepository<ArmsTeam, ArmsTeamDto> {

    override fun getAll(): Flow<List<ArmsTeam>> {
        return dao.getAll().map { entities -> entities.map { it.toArmsTeam() } }
    }

    override suspend fun insertAll(remoteList: List<ArmsTeamDto>): Result<Unit> {
        return try {
            dao.insertAll(remoteList.map { it.toArmsTeamEntity() })
            Result.success(Unit)
        } catch (e: SQLException) {
            Result.failure<Unit>(e)
        }
    }

    override suspend fun hasData(): Boolean = dao.hasData()

}