package com.example.armsapp.ui.components

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.armsapp.ui.viewmodel.PlayerViewModel

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerView(
    viewModel: PlayerViewModel,
    mediaUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val player = remember { viewModel.getOrCreatePlayer(context, mediaUrl) }

    DisposableEffect(Unit) {
        val observer =
            LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_PAUSE -> {
                        viewModel.saveState(mediaUrl)
                        viewModel.pause(mediaUrl)
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        viewModel.play(mediaUrl)
                    }

                    else -> {}
                }
            }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            viewModel.saveState(mediaUrl)
            viewModel.releasePlayer(mediaUrl)
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                this.player = player
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                useController = false
                setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
            }
        },
        modifier =
            modifier
                .fillMaxWidth()
                .height(200.dp),
    )
}

@Composable
fun VideoWithVisibilityHandler(
    viewModel: PlayerViewModel,
    mediaUrl: String,
    tag: String,
    context: Context,
    modifier: Modifier,
) {
    // Lidar com visibilidade do vídeo na tela
    val isVisible = remember { mutableStateOf(false) }
    val playerState by viewModel.playerState.collectAsStateWithLifecycle()

    // Controle do ExoPlayer - Preload, Play e Pause baseado na visibilidade
    LaunchedEffect(tag, isVisible.value) {
        if (isVisible.value) {
            // Iniciar o player ou fazer o preload se o player não estiver inicializado
            if (playerState == null) {
                viewModel.getOrCreatePlayer(context, mediaUrl)
            }

            viewModel.play(tag) // Garantir que o player comece a tocar se visível
        } else {
            viewModel.pause(tag) // Pausar quando o player sair de foco
        }
    }

    // Detectar quando o player entra e sai da tela
    val viewPortHeight = LocalDensity.current.density * 100 // Ajuste conforme necessário
    val scrollState = rememberScrollState()
    val totalHeight = scrollState.maxValue
    val currentOffset = scrollState.value

    // Atualizar visibilidade com base no deslocamento da rolagem e altura da tela
    LaunchedEffect(currentOffset) {
        isVisible.value = (currentOffset + viewPortHeight) < totalHeight
    }

    // Renderizar o player com o controle de visibilidade
    ExoPlayerView(
        viewModel = viewModel,
        mediaUrl = mediaUrl,
        modifier = modifier,
    )
}
