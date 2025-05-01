package com.example.armsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.armsapp.R
import com.example.armsapp.ui.components.AppNavigationHost
import com.example.armsapp.ui.components.ArmsTopAppBar
import com.example.armsapp.ui.components.BottomNavBar
import com.example.armsapp.ui.components.ErrorScreen
import com.example.armsapp.ui.components.LoadingScreen
import com.example.armsapp.ui.state.AppInitState
import com.example.armsapp.ui.theme.ArmsAppTheme
import com.example.armsapp.ui.viewmodel.AppViewModelProvider
import com.example.armsapp.ui.viewmodel.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewMode: MainScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by viewMode.appInitState.collectAsStateWithLifecycle()

    when (uiState) {
        is AppInitState.Error -> {
            ErrorScreen(
                message = (uiState as AppInitState.Error).message,
                onRetry = { viewMode::retryInit })
        }

        is AppInitState.Loading -> {
            LoadingScreen()
        }

        is AppInitState.Success -> {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    ArmsTopAppBar(
                        titleRes = R.string.app_name,
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        selectedItemIndex = selectedTabIndex,
                        onItemSelected = { selectedTabIndex = it },
                        navController = navController
                    )
                },
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                AppNavigationHost(
                    navController = navController,
                    onChangeIndexNavBarNavItem = { selectedTabIndex = it },
                    contentPaddingValues = innerPadding
                )
            }
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
