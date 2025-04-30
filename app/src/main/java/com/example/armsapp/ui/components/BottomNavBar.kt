package com.example.armsapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.armsapp.domain.model.BottomBarNavItem

@Composable
fun BottomNavBar(
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navController: NavController
) {
    val items = listOf(
        BottomBarNavItem.HomeScreen,
        BottomBarNavItem.WeAreScreen,
        BottomBarNavItem.WeDoScreen,
        BottomBarNavItem.SpeakScreen,
        BottomBarNavItem.ContactScreen
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        items.forEachIndexed { index, route ->
            val isSelected = selectedItemIndex == index
            val labelText = stringResource(id = route.label)

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onItemSelected(index)
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) route.selectedIcon else route.unselectedIcon,
                        contentDescription = labelText
                    )
                },
                label = {
                    Text(text = labelText)
                }
            )
        }
    }
}