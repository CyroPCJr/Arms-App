package com.example.armsapp.domain.model

import androidx.annotation.StringRes
import com.example.armsapp.data.local.entities.ArmsTeamEntity

data class ArmsTeam(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val jobPosition: Int,
    val instagramLabel: String,
    val instagramUrl: String,
    val imageUrl: String,
)

fun ArmsTeam.toArmsTeamEntity() = ArmsTeamEntity(
    id = id,
    name = name.toString(),
    jobPosition = jobPosition.toString(),
    instagramLabel = instagramLabel,
    instagramUrl = instagramUrl,
    image = imageUrl
)