package com.example.armsapp.network.dto

import com.example.armsapp.data.local.entities.ProjectEntity
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = "",
)

fun ProjectDto.toProjectEntity(): ProjectEntity =
    ProjectEntity(
        id = id,
        urlImage = urlImage,
        linkPage = linkPage,
        name = name,
        type = type,
    )
