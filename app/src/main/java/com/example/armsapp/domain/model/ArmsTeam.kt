package com.example.armsapp.domain.model

import com.example.armsapp.data.local.entities.ArmsTeamEntity

data class ArmsTeam(
    val id: Int,
    val name: String,
    val jobPosition: String,
    val instagramLabel: String,
    val instagramUrl: String,
    val imageUrl: String,
)

fun ArmsTeam.toArmsTeamEntity() = ArmsTeamEntity(
    id = id,
    name = name,
    jobPosition = jobPosition,
    instagramLabel = instagramLabel,
    instagramUrl = instagramUrl,
    image = imageUrl
)