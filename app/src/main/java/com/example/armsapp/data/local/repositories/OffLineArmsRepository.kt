package com.example.armsapp.data.local.repositories

import com.example.armsapp.network.service.ApiService
import com.example.armsapp.utils.AndroidLogger
import com.example.armsapp.utils.Logger
import kotlinx.coroutines.flow.Flow

interface OfflineArmsRepo<T> {
    suspend fun fetchAndSaveArmsRepo(): Result<Unit>

    fun getArmsRepo(): Flow<List<T>>
}

class OffLineArmsRepository<T, U>(
    private val localDataSource: ArmsLocalRepository<T, U>,
    private val remoteDataSource: ApiService<U>,
    private val logger: Logger = AndroidLogger(),
) : OfflineArmsRepo<T> {
    companion object {
        private const val TAG = "OffLineArmsRepository"
    }

    override suspend fun fetchAndSaveArmsRepo(): Result<Unit> =
        if (!localDataSource.hasData()) {
            try {
                val remote = remoteDataSource.apiGetCall()
                localDataSource.insertAll(remote)
            } catch (e: Exception) {
                logger.e(TAG, "Fail to fetch projects: ${e.message}")
                Result.failure(e)
            }
        } else {
            Result.success(Unit)
        }

    override fun getArmsRepo(): Flow<List<T>> = localDataSource.getAll()
}
