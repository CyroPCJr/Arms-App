package com.example.armsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ArmsUI(modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ArmsAppTheme {
        MainScreen()
    }
}
