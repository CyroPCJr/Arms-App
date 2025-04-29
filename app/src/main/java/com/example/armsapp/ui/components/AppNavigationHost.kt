package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.armsapp.domain.model.BottomBarNavItem
import com.example.armsapp.ui.screens.contact.ContactScreen
import com.example.armsapp.ui.screens.home.HomeScreen
import com.example.armsapp.ui.screens.home.HomeScreenViewModel
import com.example.armsapp.ui.screens.weare.WeAreScreen
import com.example.armsapp.ui.screens.weare.WeAreScreenViewModel
import com.example.armsapp.ui.screens.wedo.WeDoScreen
import com.example.armsapp.ui.screens.wedo.WeDoScreenViewModel
import com.example.armsapp.ui.screens.wespeak.WeSpeakScreen
import com.example.armsapp.ui.screens.wespeak.WeSpeakScreenViewModel
import com.example.armsapp.ui.viewmodel.AppViewModelProvider
import com.example.armsapp.ui.viewmodel.PlayerViewModel

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
            val homeScreenViewModel: HomeScreenViewModel =
                viewModel(factory = AppViewModelProvider.Factory)
            val playerViewModel: PlayerViewModel = viewModel(factory = AppViewModelProvider.Factory)
            HomeScreen(
                playerViewModel = playerViewModel,
                viewModel = homeScreenViewModel,
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
            val weSpeakScreenViewModel: WeSpeakScreenViewModel =
                viewModel(factory = AppViewModelProvider.Factory)
            WeSpeakScreen(
                viewModel = weSpeakScreenViewModel,
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
            val weDoScreenViewModel: WeDoScreenViewModel =
                viewModel(factory = AppViewModelProvider.Factory)
            WeDoScreen(
                viewModel = weDoScreenViewModel,
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
            val weAreScreenViewModel: WeAreScreenViewModel =
                viewModel(factory = AppViewModelProvider.Factory)
            WeAreScreen(
                viewModel = weAreScreenViewModel,
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
