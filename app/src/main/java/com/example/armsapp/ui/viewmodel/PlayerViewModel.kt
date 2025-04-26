package com.example.armsapp.ui.viewmodel

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    private val _playerState = MutableStateFlow<ExoPlayer?>(null)
    val playerState: StateFlow<ExoPlayer?> = _playerState

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    @UnstableApi
    fun initializePlayer(context: Context, videoUrl: String) {
        if (_playerState.value == null) {

            val loadControl = DefaultLoadControl.Builder()
                .setBufferDurationsMs(
                    15000,  // mínimo de buffer para começar (15s)
                    50000,  // buffer máximo (50s)
                    3000,   // quanto precisa pra começar a tocar (3s)
                    5000 // quanto precisa pra retomar após pausa (5s)
                )
                .build()

            val exoPlayer = ExoPlayer.Builder(context).setLoadControl(loadControl).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl.toUri())
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
                addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        handleError(error)
                    }
                })
            }
            _playerState.value = exoPlayer
        }
    }

    fun releasePlayer() {
        _playerState.value?.release()
        _playerState.value = null
    }

    private fun handleError(error: PlaybackException) {
        val message = when (error.errorCode) {
            PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> "Erro de conexão"
            PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND -> "Vídeo não encontrado"
            PlaybackException.ERROR_CODE_DECODER_INIT_FAILED -> "Erro no decodificador"
            else -> error.message ?: "Erro desconhecido"
        }
        viewModelScope.launch {
            _error.emit(message)
        }
    }

}