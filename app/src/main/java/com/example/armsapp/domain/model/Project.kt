package com.example.armsapp.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Project(
    val id: Int,
    val urlImage: String,
    val linkPage: String,
    val name: String,
    val type: String = "",
)