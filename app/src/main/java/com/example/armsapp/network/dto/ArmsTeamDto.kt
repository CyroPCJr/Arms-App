package com.example.armsapp.network.dto

import com.example.armsapp.data.local.entities.ArmsTeamEntity
import kotlinx.serialization.Serializable

@Serializable
data class ArmsTeamDto(
    val id: Int,
    val name: String,
    val jobPosition: String,
    val instagramLabel: String,
    val instagramUrl: String,
    val image: String
)

fun ArmsTeamDto.toArmsTeamEntity() = ArmsTeamEntity(
    id = id,
    name = name,
    jobPosition = jobPosition,
    instagramLabel = instagramLabel,
    instagramUrl = instagramUrl,
    image = image
)

