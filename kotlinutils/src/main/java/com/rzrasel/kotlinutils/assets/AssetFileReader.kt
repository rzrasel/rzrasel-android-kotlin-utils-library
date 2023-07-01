package com.rzrasel.kotlinutils.assets

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream

object AssetFileReader {
    suspend fun read(context: Context, fileName: String): String? = withContext(Dispatchers.IO) {
        try {
            val bufferReader = context.assets.open(fileName).bufferedReader()
            bufferReader.use(BufferedReader::readText).toString()
        } catch (ex: IOException) {
            null
        }
    }

    suspend fun inputStream(context: Context, fileName: String): InputStream? =
        withContext(Dispatchers.IO) {
            try {
                context.assets.open(fileName)
            } catch (ex: IOException) {
                null
            }
        }

    //imageView.setImageDrawable(myDrawable)
    suspend fun drawable(context: Context, fileName: String, srcName: String? = null): Drawable? =
        inputStream(context, fileName)?.let {
            Drawable.createFromStream(it, srcName)
        } ?: run {
            null
        }

    //imageView.setImageBitmap(bm)
    suspend fun bitmap(context: Context, fileName: String): Bitmap? =
        inputStream(context, fileName)?.let {
            BitmapFactory.decodeStream(it)
        } ?: run {
            null
        }

    suspend fun audioDescriptor(context: Context, fileName: String): AssetFileDescriptor? =
        withContext(Dispatchers.IO) {
            try {
                context.assets.openFd(fileName)
            } catch (ex: IOException) {
                null
            }
        }

    //fun audioMedia(descriptor: AssetFileDescriptor) =
    suspend fun closeAudioDescriptor(descriptor: AssetFileDescriptor) = descriptor.close()
}