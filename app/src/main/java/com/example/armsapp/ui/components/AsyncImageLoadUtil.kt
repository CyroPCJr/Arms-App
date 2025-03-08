package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun LoadImages(
    urlsContent: String,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(data = urlsContent)
            .crossfade(true)
            .build(),
        contentDescription = null,
        loading = { CircularProgressIndicator() },
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}