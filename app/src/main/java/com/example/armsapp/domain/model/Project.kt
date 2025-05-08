package com.example.armsapp.domain.model

import androidx.compose.runtime.Immutable
import com.example.armsapp.data.local.entities.ProjectEntity
import com.example.armsapp.network.dto.ProjectDto

@Immutable
data class Project(
    val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = "",
)

fun Project.toProjectDto() =
    ProjectDto(
        id = id,
        urlImage = urlImage,
        linkPage = linkPage,
        name = name,
        type = type,
    )

fun Project.toProjectEntity() =
    ProjectEntity(
        id = id,
        urlImage = urlImage,
        linkPage = linkPage,
        name = name,
        type = type,
    )
