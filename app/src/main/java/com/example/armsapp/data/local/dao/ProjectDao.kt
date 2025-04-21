package com.example.armsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.armsapp.domain.model.Project

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    suspend fun getAll(): List<Project>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(projects: List<Project>)
}