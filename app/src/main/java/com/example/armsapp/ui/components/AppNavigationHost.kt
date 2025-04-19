package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.armsapp.domain.model.BottomBarNavItem
import com.example.armsapp.ui.contact.ContactScreen
import com.example.armsapp.ui.home.HomeScreen
import com.example.armsapp.ui.weare.WeAreScreen
import com.example.armsapp.ui.wedo.WeDoScreen
import com.example.armsapp.ui.wespeak.WeSpeakScreen

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    onChangeIndexNavBarNavItem: (Int) -> Unit,
    contentPaddingValues: PaddingValues
) {
    var emailSent by rememberSaveable { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = BottomBarNavItem.HomeScreen.route
    ) {
        composable(BottomBarNavItem.HomeScreen.route) {
            HomeScreen(
                onClickWeDoScreen = {
                    navigateTo(navController, BottomBarNavItem.WeDoScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    }
                },
                onClickWeAreScreen = {
                    navigateTo(navController, BottomBarNavItem.WeAreScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    }
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(BottomBarNavItem.SpeakScreen.route) {
            WeSpeakScreen(
                onClickWeDoScreen = {
                    navigateTo(navController, BottomBarNavItem.WeDoScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    }
                },
                onClickWeAreScreen = {
                    navigateTo(navController, BottomBarNavItem.WeAreScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    }
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(BottomBarNavItem.WeDoScreen.route) {
            WeDoScreen(
                onClickWeAreScreen = {
                    navigateTo(navController, BottomBarNavItem.WeAreScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    }
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(BottomBarNavItem.ContactScreen.route) {

            ContactScreen(
                emailSent = emailSent,
                onClickSendEmail = {
                    emailSent = true
                },
                contentPaddingValues = contentPaddingValues
            )

            LaunchedEffect(emailSent) {
                if (emailSent) {
                    emailSent = false
                }
            }
        }

        composable(BottomBarNavItem.WeAreScreen.route) {
            WeAreScreen(
                onClickWeDoScreen = {
                    navigateTo(navController, BottomBarNavItem.WeDoScreen.route) {
                        onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    }
                },
                contentPaddingValues = contentPaddingValues
            )
        }
    }
}

private fun navigateTo(
    navController: NavHostController,
    route: String,
    onSelect: () -> Unit
) {
    onSelect()
    navController.navigate(route)
}
