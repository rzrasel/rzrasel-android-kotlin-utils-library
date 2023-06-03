package com.rzrasel.rztutorial.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.util.Size
import android.view.Display
import androidx.core.hardware.display.DisplayManagerCompat

object AppScreen {
    fun size(activity: Activity, context: Context): Size {
        var sizeUnit: Size = Size(0, 0)
        var width: Int
        var height: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val defaultDisplay =
                DisplayManagerCompat.getInstance(context).getDisplay(Display.DEFAULT_DISPLAY)
            val displayContext = context.createDisplayContext(defaultDisplay!!)

            width = displayContext.resources.displayMetrics.widthPixels
            height = displayContext.resources.displayMetrics.heightPixels
            sizeUnit = Size(width, height)
        } else {
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            height = displayMetrics.heightPixels
            width = displayMetrics.widthPixels
            sizeUnit = Size(width, height)
        }
        return sizeUnit
    }

    fun pxFromDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpFromPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun dpToPx(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
//88.px
