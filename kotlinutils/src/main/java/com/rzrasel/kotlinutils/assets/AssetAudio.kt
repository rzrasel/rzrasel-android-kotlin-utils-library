package com.rzrasel.kotlinutils.assets

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

object AssetAudio {
    private suspend fun descriptor(context: Context, fileName: String): AssetFileDescriptor? =
        withContext(
            Dispatchers.IO
        ) {
            try {
                context.assets.openFd(fileName)
            } catch (ex: IOException) {
                null
            }
        }

    suspend fun onLoadMedia(context: Context, player: MediaPlayer, fileName: String) {
        try {
            val descriptor: AssetFileDescriptor? = descriptor(context, fileName)
            descriptor?.let {
                player.setDataSource(
                    it.fileDescriptor,
                    it.startOffset,
                    it.length
                )
                closeDescriptor(it)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private suspend fun closeDescriptor(descriptor: AssetFileDescriptor) = descriptor.close()
}
/*

public class PlayerExample {
    MediaPlayer p = null;
    private void playSound(String fileName) {
        p = new MediaPlayer();
        try {
            AssetFileDescriptor afd = ctx.getAssets().openFd(fileName);
            p.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            p.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        p.start();
    }
}

*/