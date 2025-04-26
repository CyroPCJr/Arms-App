package com.example.armsapp.ui.components

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.armsapp.ui.viewmodel.PlayerViewModel

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerView(
    viewModel: PlayerViewModel,
    mediaUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val player by viewModel.playerState.collectAsState()

    LaunchedEffect(mediaUrl) {
        viewModel.initializePlayer(context, mediaUrl)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.releasePlayer()
        }
    }

    player?.let { exoPlayer ->
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    this.player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    useController = false
                    setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}