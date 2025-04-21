package com.example.armsapp.domain.model

import androidx.annotation.StringRes

data class ArmsTeam(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val jobPosition: Int,
    val instagramLabel: String,
    val instagramUrl: String,
    val imageUrl: String,
)
