package com.rzrasel.kotlinutils

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import java.lang.Exception

object AssetAudio {
    private fun descriptor(context: Context, fileName: String): AssetFileDescriptor =
        context.assets.openFd(fileName)

    fun onLoadMedia(context: Context, player: MediaPlayer, fileName: String) {
        try {
            val descriptor: AssetFileDescriptor = descriptor(context, fileName)
            player.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            closeDescriptor(descriptor)
        } catch (e: Exception) {
            println("DEBUG_LOG_PRINT: AssetAudio printStackTrace")
            e.printStackTrace()
        }
    }

    private fun closeDescriptor(descriptor: AssetFileDescriptor) = descriptor.close()
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