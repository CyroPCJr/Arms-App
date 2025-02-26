package com.example.armsapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun LoadImages(
    urlsContent: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = urlsContent,
        contentDescription = null,
        modifier = modifier
    )
}

