package com.example.armsapp.network.dto

data class ProjectDto(
    val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = ""
)
