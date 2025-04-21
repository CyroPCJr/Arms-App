package com.example.armsapp.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = ""
)
