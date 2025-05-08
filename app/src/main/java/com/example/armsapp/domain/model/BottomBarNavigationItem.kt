package com.example.armsapp.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.armsapp.R

sealed class BottomBarNavigationItem(
    val id: Int,
    val route: String,
    @StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object HomeScreen : BottomBarNavigationItem(
        id = 0,
        route = "Principal",
        label = R.string.botton_bar_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    )

    data object WeAreScreen : BottomBarNavigationItem(
        id = 1,
        route = "Somos",
        label = R.string.botton_bar_we_are,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    )

    data object WeDoScreen : BottomBarNavigationItem(
        id = 2,
        route = "Fazemos",
        label = R.string.botton_bar_we_do,
        selectedIcon = Icons.Filled.Construction,
        unselectedIcon = Icons.Outlined.Construction,
    )

    data object SpeakScreen : BottomBarNavigationItem(
        id = 3,
        route = "Falamos",
        label = R.string.botton_bar_speak,
        selectedIcon = Icons.Filled.RecordVoiceOver,
        unselectedIcon = Icons.Outlined.RecordVoiceOver,
    )

    data object ContactScreen : BottomBarNavigationItem(
        id = 4,
        route = "Contato",
        label = R.string.botton_bar_contact,
        selectedIcon = Icons.Filled.Contacts,
        unselectedIcon = Icons.Outlined.Contacts,
    )
}
