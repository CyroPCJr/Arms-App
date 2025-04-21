package com.example.armsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.armsapp.domain.model.ArmsTeam

@Dao
interface ArmsTeamDao {
    @Query("SELECT * FROM armsTeam")
    suspend fun getAll(): List<ArmsTeam>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(armsTeamList: List<ArmsTeam>)
}