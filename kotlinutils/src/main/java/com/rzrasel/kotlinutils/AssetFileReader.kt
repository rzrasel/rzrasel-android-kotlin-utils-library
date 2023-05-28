package com.rzrasel.kotlinutils

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import java.io.BufferedReader
import java.io.InputStream

object AssetFileReader {
    fun read(context: Context, fileName: String): String =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText).toString()

    fun inputStream(context: Context, fileName: String): InputStream =
        context.assets
            .open(fileName)

    //imageView.setImageDrawable(myDrawable)
    fun drawable(context: Context, fileName: String): Drawable? =
        Drawable.createFromStream(inputStream(context, fileName), null)

    //imageView.setImageBitmap(bm)
    fun bitmap(context: Context, fileName: String): Bitmap? =
        BitmapFactory.decodeStream(inputStream(context, fileName))

    fun audioDescriptor(context: Context, fileName: String): AssetFileDescriptor =
        context.assets.openFd(fileName)

    //fun audioMedia(descriptor: AssetFileDescriptor) =
    fun closeAudioDescriptor(descriptor: AssetFileDescriptor) = descriptor.close()
}