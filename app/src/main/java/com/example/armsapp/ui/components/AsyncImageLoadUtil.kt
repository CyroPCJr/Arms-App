package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.armsapp.R

@Composable
fun LoadImages(
    imageUrl: String,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.ic_load_image),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 1080f / 920f)
    )
}