package com.example.armsapp.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArmsTeamDto(
    val name: String,
    val jobPosition: String,
    val instagramLabel: String,
    val instagramUrl: String,
    val image: String
)
