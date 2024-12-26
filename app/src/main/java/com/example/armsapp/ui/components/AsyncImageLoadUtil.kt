package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun LoadImages(modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://arms.com.br/wp-content/uploads/2024/11/Rectangle-65.png",
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

