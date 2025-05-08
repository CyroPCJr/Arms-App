package com.example.armsapp.data.local.repositories

import kotlinx.coroutines.flow.Flow

interface ArmsLocalRepository<T, U> {
    fun getAll(): Flow<List<T>>

    suspend fun hasData(): Boolean

    suspend fun insertAll(remoteList: List<U>): Result<Unit>
}
