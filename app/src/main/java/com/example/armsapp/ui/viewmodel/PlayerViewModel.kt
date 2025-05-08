package com.example.armsapp.ui.viewmodel

import android.content.Context
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import com.example.armsapp.utils.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerViewModel(
    private val logger: Logger,
) : ViewModel() {
    private val players = mutableMapOf<String, ExoPlayer>()
    private val currentPositions = mutableMapOf<String, Long>()

    private val _playerState = MutableStateFlow<ExoPlayer?>(null)
    val playerState: StateFlow<ExoPlayer?> = _playerState

    @OptIn(UnstableApi::class)
    fun getOrCreatePlayer(
        context: Context,
        videoUrl: String,
    ): ExoPlayer =
        players.getOrPut(videoUrl) {
            val loadControl =
                DefaultLoadControl
                    .Builder()
                    .setBufferDurationsMs(
                        15000,
                        50000,
                        3000,
                        5000,
                    ).build()

            ExoPlayer.Builder(context).setLoadControl(loadControl).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = false
                repeatMode = Player.REPEAT_MODE_ONE
                addListener(
                    object : Player.Listener {
                        override fun onPlayerError(error: PlaybackException) {
                            logger.e("ExoPlayer", "Error: ${error.message}")
                        }
                    },
                )
            }
        }

    fun play(videoUrl: String) {
        players[videoUrl]?.play()
    }

    fun pause(videoUrl: String) {
        players[videoUrl]?.pause()
    }

    fun saveState(videoUrl: String) {
        players[videoUrl]?.let {
            currentPositions[videoUrl] = it.currentPosition
        }
    }

    fun releasePlayer(videoUrl: String) {
        players[videoUrl]?.release()
        players.remove(videoUrl)
        currentPositions.remove(videoUrl)
    }

    override fun onCleared() {
        super.onCleared()
        players.values.forEach { it.release() }
        players.clear()
    }
}
