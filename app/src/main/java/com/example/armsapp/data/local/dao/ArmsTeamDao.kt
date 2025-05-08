package com.example.armsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.armsapp.data.local.entities.ArmsTeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArmsTeamDao {
    @Query("SELECT * FROM armsTeam")
    fun getAll(): Flow<List<ArmsTeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(armsTeamList: List<ArmsTeamEntity>)

    @Query("SELECT EXISTS(SELECT 1 FROM armsTeam LIMIT 1)")
    suspend fun hasData(): Boolean
}
