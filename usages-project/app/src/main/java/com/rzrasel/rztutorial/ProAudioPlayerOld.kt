package com.rzrasel.rztutorial

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ProAudioPlayerOld(argBuilder: Builder) : MediaPlayer() {
    private val activity: Activity? = null
    private var context: Context? = null

    //
    private var playbackListener: OnPlaybackListener? = null

    //
    private var mediaPlayerRunnable: MediaPlayerRunnable? = null
    private var mediaPlayerThread: MediaPlayerThread? = null
    private var mediaPlayerHandler: MediaPlayerHandler? = null
    private var handlerMessage: Message? = null

    //
    // Media player
    private var mediaPlayer: MediaPlayer? = null
    private var isPlayOnlineStream = false
    private val isDebug = true

    init {
        mediaPlayer = this
        mediaPlayerThread = MediaPlayerThread()
        mediaPlayerRunnable = MediaPlayerRunnable()
        mediaPlayerHandler = MediaPlayerHandler()
        /*mediaPlayerHandler.post(mediaPlayerThread);
        handlerMessage = mediaPlayerHandler.obtainMessage();*/
        //mediaPlayerHandler.sendEmptyMessage(1);
        //
        /*mediaPlayerHandler.post(mediaPlayerThread);
        handlerMessage = mediaPlayerHandler.obtainMessage();*/
        //mediaPlayerHandler.sendEmptyMessage(1);
        //
        context = argBuilder.context
        playbackListener = argBuilder.playbackListener
        isPlayOnlineStream = argBuilder.isPlayOnlineStream
        initMediaPlayer()
    }

    private fun initMediaPlayer() {
        // Media player
        //mediaPlayer = new MediaPlayer();
        mediaPlayer?.setOnCompletionListener(MediaCompletionListener())
    }

    fun onPlay(argSongPath: String?) {
        val thread: Thread = object : Thread() {
            override fun run() {
                onPlayAudio(argSongPath)
            }
        }
        thread.start()
    }
    fun onPlay(argAudioId: Int) {
        val thread: Thread = object : Thread() {
            override fun run() {
                //Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.sleep_away);
                val uri =
                    Uri.parse("android.resource://" + context!!.packageName + "/" + argAudioId)
                //mediaPlayer = MediaPlayer.create(context, argAudioId);
                try {
                    //mediaPlayer = new MediaPlayer();
                    if (isPlay()) {
                        mediaPlayer?.stop()
                    }
                    mediaPlayer?.reset()
                    //mediaPlayer.release();
                    //mediaPlayer = MediaPlayer.create(context, argAudioId);
                    mediaPlayer?.setDataSource(context!!, uri)
                    //
                    /*float speed = 0.75f;
                    speed = 0.5f;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                    }*/
                    //
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer?.prepare()
                    //mediaPlayer.start();
                    mediaPlayer?.setOnPreparedListener(MediaPreparedListener())
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //onPlayAudio(null);
            }
        }
        thread.start()
    }

    private fun onPlayAudio(argSongPath: String?) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                if (argSongPath != null) {
                    context?.let { mediaPlayer!!.setDataSource(it, Uri.parse(argSongPath)) }
                }
                if (isPlayOnlineStream) {
                    mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                }
                if (argSongPath != null) {
                    mediaPlayer!!.prepare()
                }
                //mediaPlayer.start();
                mediaPlayer!!.setOnPreparedListener(MediaPreparedListener())
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun onStart() {
        mediaPlayer?.start()
    }

    fun onStop() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
        }
    }

    fun onPause() {
        if (isPlaying) {
            mediaPlayer?.pause()
        }
    }

    fun onReset() {}

    override fun getDuration(): Int {
        return mediaPlayer?.getDuration() ?: 0
    }

    fun getCurrentDuration(): Int {
        return mediaPlayer?.getCurrentPosition() ?: 0
    }

    fun getSpeed(): Float {
        if (mediaPlayer != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return mediaPlayer!!.getPlaybackParams().getSpeed()
            }
        }
        return 1f
    }


    fun setSpeed(argSpeed: Float) {
        /*if (mediaPlayer != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(argSpeed));
            }
        }*/
        Thread {
            if (mediaPlayer != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mediaPlayer!!.setPlaybackParams(
                        mediaPlayer!!.getPlaybackParams().setSpeed(argSpeed)
                    )
                }
            }
        }.start()
    }

    override fun seekTo(argSeekTime: Int) {
        mediaPlayer?.seekTo(argSeekTime)
    }

    fun isPlay(): Boolean {
        return mediaPlayer?.isPlaying() ?: false
    }

    fun onPlayPause() {
        if (isPlaying) {
            onPause()
        } else {
            onStart()
        }
    }

    fun onForward(argSeekTime: Int) {
        val currentPosition: Int = getCurrentDuration()
        if (currentPosition + argSeekTime <= duration) {
            seekTo(currentPosition + argSeekTime)
        } else {
            seekTo(duration)
        }
    }

    fun onBackward(argSeekTime: Int) {
        val currentPosition: Int = getCurrentDuration()
        if (currentPosition - argSeekTime >= 0) {
            seekTo(currentPosition - argSeekTime)
        } else {
            seekTo(0)
        }
    }

    fun onNext(argSongPath: String?) {
        onPlay(argSongPath)
    }

    fun isPlayOnlineStream(argIsPlayOnlineStream: Boolean) {
        this.isPlayOnlineStream = argIsPlayOnlineStream
    }

    fun onPrevious(argSongPath: String?) {
        onPlay(argSongPath)
    }

    fun onDestroy() {
        mediaPlayer?.release()
        if (mediaPlayer != null) {
            mediaPlayer = null
        }
    }


    class Builder {
        private var activity: Activity? = null
        var context: Context? = null
        var playbackListener: OnPlaybackListener? = null
        var isPlayOnlineStream = false

        //
        fun with(argActivity: Activity?): Builder {
            activity = argActivity
            return this
        }

        fun with(argContext: Context?): Builder {
            context = argContext
            return this
        }

        fun setPlayerListener(argPlaybackListener: OnPlaybackListener?): Builder {
            playbackListener = argPlaybackListener
            return this
        }

        fun isPlayOnlineStream(argIsPlayOnlineStream: Boolean): Builder {
            isPlayOnlineStream = argIsPlayOnlineStream
            isPlayOnlineStream(isPlayOnlineStream)
            return this
        }

        fun build(argContext: Context?): ProAudioPlayerOld {
            context = argContext
            return ProAudioPlayerOld(this)
        }
    }

    inner class MediaPlayerRunnable : Runnable {
        override fun run() {
            handlerMessage = mediaPlayerHandler?.obtainMessage()
        }
    }

    inner class MediaPlayerThread : Thread() {
        override fun run() {
            handlerMessage = mediaPlayerHandler?.obtainMessage()
        }
    }

    internal class MediaPlayerHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(argMessage: Message) {
            //DebugLogger.log("HANDLER_TEST", isDebug)
        }
    }

    inner class MediaPreparedListener : OnPreparedListener {
        override fun onPrepared(argMediaPlayer: MediaPlayer) {
            mediaPlayer = argMediaPlayer
            mediaPlayer!!.start()
        }
    }

    inner class MediaCompletionListener : OnCompletionListener {
        override fun onCompletion(argMediaPlayer: MediaPlayer) {
            playbackListener?.onCompletion()
        }
    }


    interface OnPlaybackListener {
        fun onCompletion()
    }
}

/*
private var audioPlayer: ProAudioPlayer? = null
audioPlayer = ProAudioPlayer.Builder()
            .setPlayerListener(AudioPlaybackListener())
            .build(context)
*/