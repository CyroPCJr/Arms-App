package com.example.armsapp.ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.armsapp.R
import com.example.armsapp.data.topLevelRoutes
import com.example.armsapp.model.BottomBarNavItem
import com.example.armsapp.ui.contact.ContactScreen
import com.example.armsapp.ui.home.HomeScreen
import com.example.armsapp.ui.theme.ArmsAppTheme
import com.example.armsapp.ui.weare.WeAreScreen
import com.example.armsapp.ui.wedo.WeDoScreen
import com.example.armsapp.ui.wespeak.WeSpeakScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                ArmsTopAppBar(
                    titleBar = R.string.app_name,
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    topLevelRoutes.forEachIndexed { index, topLevelRoute ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(topLevelRoute.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }

                            },
                            label = {
                                Text(text = stringResource(topLevelRoute.label))
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        topLevelRoute.selectedIcon
                                    } else topLevelRoute.unselectedIcon,
                                    contentDescription = stringResource(topLevelRoute.label)
                                )
                            },
                        )
                    }
                }
            }) { innerPadding ->
            NavigationHost(
                navController = navController,
                onChangeIndexNavBarNavItem = { selectedItemIndex = it },
                contentPaddingValues = innerPadding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArmsTopAppBar(
    @StringRes titleBar: Int,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = stringResource(titleBar))
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onChangeIndexNavBarNavItem: (Int) -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavItem.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = BottomBarNavItem.HomeScreen.route)
        {
            HomeScreen(
                onClickWeDoScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    navController.navigate(BottomBarNavItem.WeDoScreen.route)

                },
                onClickWeAreScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    navController.navigate(BottomBarNavItem.WeAreScreen.route)
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(route = BottomBarNavItem.SpeakScreen.route) {
            WeSpeakScreen(
                onClickWeDoScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    navController.navigate(BottomBarNavItem.WeDoScreen.route)
                },
                onClickWeAreScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    navController.navigate(BottomBarNavItem.WeAreScreen.route)
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(route = BottomBarNavItem.WeDoScreen.route) {
            WeDoScreen(
                onClickWeAreScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeAreScreen.id)
                    navController.navigate(BottomBarNavItem.WeAreScreen.route)
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(route = BottomBarNavItem.ContactScreen.route) {
            val context = LocalContext.current
            ContactScreen(
                onClickSendEmail = {
                    Toast.makeText(context, "email sent", Toast.LENGTH_LONG).show()
                },
                contentPaddingValues = contentPaddingValues
            )
        }

        composable(route = BottomBarNavItem.WeAreScreen.route) {
            WeAreScreen(
                onClickWeDoScreen = {
                    onChangeIndexNavBarNavItem(BottomBarNavItem.WeDoScreen.id)
                    navController.navigate(BottomBarNavItem.WeDoScreen.route)
                },
                contentPaddingValues = contentPaddingValues
            )
        }


    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ArmsAppTheme {
        MainScreen()
    }
}
