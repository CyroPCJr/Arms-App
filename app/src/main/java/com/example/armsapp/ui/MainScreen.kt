package com.example.armsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.armsapp.R
import com.example.armsapp.ui.components.AppNavigationHost
import com.example.armsapp.ui.components.ArmsTopAppBar
import com.example.armsapp.ui.components.BottomNavBar
import com.example.armsapp.ui.theme.ArmsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var selectTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(topBar = {
            ArmsTopAppBar(
                titleRes = R.string.app_name, scrollBehavior = scrollBehavior
            )

        }, bottomBar = {
            BottomNavBar(
                selectedItemIndex = selectTabIndex,
                onItemSelected = { selectTabIndex = it },
                navController = navController
            )
        }) { innerPadding ->
            AppNavigationHost(
                navController = navController,
                onChangeIndexNavBarNavItem = { selectTabIndex = it },
                contentPaddingValues = innerPadding
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
