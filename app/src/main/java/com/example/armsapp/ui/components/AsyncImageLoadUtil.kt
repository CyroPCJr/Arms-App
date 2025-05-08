package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.armsapp.R
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun LoadImages(
    imageUrl: String,
    modifier: Modifier = Modifier,
    aspectRatio: Float? = 1080f / 920f,
) {
    val finalModifier =
        aspectRatio?.let {
            modifier
                .fillMaxWidth()
                .aspectRatio(it)
        } ?: modifier.fillMaxWidth()

    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
        contentDescription = null,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.ic_load_image),
        contentScale = ContentScale.Fit,
        modifier = finalModifier,
    )
}

@Preview(name = "LoadImages Preview", showBackground = true)
@Composable
fun PreviewLoadImages() {
    ArmsAppTheme {
        LoadImages(
            imageUrl = "https://via.placeholder.com/300x200", // URL fake só pra visual
            aspectRatio = 16f / 9f,
        )
    }
}
