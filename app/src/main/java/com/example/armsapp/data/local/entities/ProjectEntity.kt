package com.example.armsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.armsapp.domain.model.Project

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = ""
)

fun ProjectEntity.toProject() = Project(
    id = id,
    urlImage = urlImage,
    linkPage = linkPage,
    name = name,
    type = type
)
