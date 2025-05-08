package com.example.armsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.armsapp.domain.model.ArmsTeam

@Entity(tableName = "armsTeam")
data class ArmsTeamEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val jobPosition: String,
    val instagramLabel: String,
    val instagramUrl: String,
    val image: String,
)

fun ArmsTeamEntity.toArmsTeam() =
    ArmsTeam(
        id = id,
        name = name,
        jobPosition = jobPosition,
        instagramLabel = instagramLabel,
        instagramUrl = instagramUrl,
        imageUrl = image,
    )
