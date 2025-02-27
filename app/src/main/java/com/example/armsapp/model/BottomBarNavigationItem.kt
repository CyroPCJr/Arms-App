package com.example.armsapp.model

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

sealed class BottomBarNavItem(
    val id: Int,
    val route: String,
    @StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {

    companion object {
        val entries =
            BottomBarNavItem::class::sealedSubclasses.get().sortedBy { it.objectInstance!!.id }
    }

    data object HomeScreen : BottomBarNavItem(
        id = 0,
        route = "Principal",
        label = R.string.botton_bar_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object WeAreScreen : BottomBarNavItem(
        id = 1,
        route = "Somos",
        label = R.string.botton_bar_we_are,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    data object WeDoScreen : BottomBarNavItem(
        id = 2,
        route = "Fazemos",
        label = R.string.botton_bar_we_do,
        selectedIcon = Icons.Filled.Construction,
        unselectedIcon = Icons.Outlined.Construction
    )

    data object SpeakScreen : BottomBarNavItem(
        id = 3,
        route = "Falamos",
        label = R.string.botton_bar_speak,
        selectedIcon = Icons.Filled.RecordVoiceOver,
        unselectedIcon = Icons.Outlined.RecordVoiceOver
    )

    data object ContactScreen : BottomBarNavItem(
        id = 4,
        route = "Contato",
        label = R.string.botton_bar_contact,
        selectedIcon = Icons.Filled.Contacts,
        unselectedIcon = Icons.Outlined.Contacts
    )
}
