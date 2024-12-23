package com.example.armsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.armsapp.ui.ArmsUI
import com.example.armsapp.ui.theme.ArmsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArmsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArmsUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}