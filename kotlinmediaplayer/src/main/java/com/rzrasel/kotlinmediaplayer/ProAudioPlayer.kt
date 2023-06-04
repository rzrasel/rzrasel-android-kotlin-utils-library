package com.rzrasel.kotlinmediaplayer

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ProAudioPlayer(private val builder: Builder) {
    private var activity: Activity?
    private var context: Context?

    //
    private var mediaPlayer: MediaPlayer? = null
    private var playbackListener: OnPlaybackListener?
    private var currentLength: Int = 0
    private var isPlayerPrepared = false

    init {
        activity = builder.activity
        context = builder.context
        playbackListener = builder.playbackListener
        //
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnCompletionListener(MediaCompletionListener())
    }

    fun isPrepared(): Boolean {
        return isPlayerPrepared
    }

    fun isPlaying(): Boolean {
        if (mediaPlayer == null) {
            return false
        }
        return mediaPlayer!!.isPlaying
    }

    fun onPlayAssetAudio(assetPath: String) {
        try {
            if (isPlaying()) {
                mediaPlayer?.stop()
            }
            mediaPlayer?.let {
                it.reset()
                context?.let { it1 -> AssetAudio.onLoadMedia(it1, it, assetPath) }
                it.setOnPreparedListener(MediaPreparedListener())
                it.prepareAsync()
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    class Builder {
        var activity: Activity? = null
        var context: Context? = null
        var playbackListener: OnPlaybackListener? = null

        fun with(activity: Activity): Builder {
            this.activity = activity
            return this@Builder
        }

        fun with(context: Context): Builder {
            this.context = context
            return this@Builder
        }

        fun setPlayerListener(playbackListener: OnPlaybackListener): Builder {
            this.playbackListener = playbackListener
            return this@Builder
        }

        fun build(context: Context): ProAudioPlayer {
            this.context = context
            return ProAudioPlayer(this)
        }
    }

    fun onPause() {
        if (isPlaying()) {
            mediaPlayer?.let {
                it.apply {
                    currentLength = currentPosition
                    pause()
                }
            }
        }
    }

    fun onResume() {
        if (!isPlaying() && isPrepared()) {
            mediaPlayer?.let {
                it.apply {
                    seekTo(currentLength)
                    start()
                }
            }
        }
    }

    fun onDestroy() {
        mediaPlayer?.release()
        isPlayerPrepared = false
    }

    inner class MediaPreparedListener : MediaPlayer.OnPreparedListener {
        override fun onPrepared(argMediaPlayer: MediaPlayer) {
            mediaPlayer = argMediaPlayer
            isPlayerPrepared = true
            mediaPlayer!!.start()

        }
    }

    inner class MediaCompletionListener : MediaPlayer.OnCompletionListener {
        override fun onCompletion(mediaPlayer: MediaPlayer) {
            playbackListener?.onCompletion()
        }
    }

    interface OnPlaybackListener {
        fun onCompletion()
    }
}