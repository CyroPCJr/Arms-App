package com.example.armsapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.armsapp.R
import com.example.armsapp.model.BottomBarNavItem
import com.example.armsapp.ui.home.HomeScreen
import com.example.armsapp.ui.theme.ArmsAppTheme

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
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    BottomBarNavItem.entries.forEachIndexed { index, item ->
                        val itemInstance = item.objectInstance!!
                        NavigationRailItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(itemInstance.route)
                            },
                            label = {
                                Text(text = stringResource(itemInstance.label))
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        itemInstance.selectedIcon
                                    } else itemInstance.unselectedIcon,
                                    contentDescription = stringResource(itemInstance.label)
                                )
                            },
                        )
                    }

                }
            }) { innerPadding ->
            NavigationHost(navController = navController, contentPaddingValues = innerPadding)
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
    navController: NavHostController,
    contentPaddingValues: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavItem.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = BottomBarNavItem.HomeScreen.route)
        {
            HomeScreen(contentPaddingValues = contentPaddingValues)
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
