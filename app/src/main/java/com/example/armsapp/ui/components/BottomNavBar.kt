package com.example.armsapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.armsapp.data.local.topLevelRoutes

@Composable
fun BottomNavBar(
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navController: NavController
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        topLevelRoutes.forEachIndexed { index, topLevelRoute ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onItemSelected(index)
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(stringResource(topLevelRoute.label)) },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index)
                            topLevelRoute.selectedIcon
                        else
                            topLevelRoute.unselectedIcon,
                        contentDescription = stringResource(topLevelRoute.label)
                    )
                }
            )
        }
    }
}