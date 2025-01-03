package com.example.armsapp.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import com.example.armsapp.BuildConfig
import com.example.armsapp.R
import com.example.armsapp.ui.components.ExoPlayerView
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.theme.ArmsAppTheme
import com.example.armsapp.ui.viewmodels.ArmsUIViewModel

@UnstableApi
@Composable
fun ArmsUI(modifier: Modifier = Modifier) {
    Column {
        LoadImages(ArmsUIViewModel.logoImageUrls, modifier = modifier)
        Descriptions()
        ExoPlayerView(BuildConfig.urlsReelsProject)
    }
}


@Composable
fun Descriptions(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.title))
        Text(text = stringResource(R.string.sub_title))
        //TODO Componente de video aqui

        Row {
            Text(text = stringResource(R.string.sub_title2), modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.sub_title3),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}


@OptIn(UnstableApi::class)
@Preview(name = "ArmsUI", showBackground = true)
@Composable
fun ArmsUIPreview() {
    ArmsAppTheme {
        ArmsUI()
    }
}