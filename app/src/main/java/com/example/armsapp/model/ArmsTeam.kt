package com.example.armsapp.model

import androidx.annotation.StringRes

data class ArmsTeam(
    @StringRes val name: Int,
    @StringRes val jobPosition: Int,
    val instagramLabel: String,
    val instagramUri: String,
    val imageUrl: String,
)
