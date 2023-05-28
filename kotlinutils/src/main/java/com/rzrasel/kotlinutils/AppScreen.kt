package com.rzrasel.kotlinutils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import androidx.core.hardware.display.DisplayManagerCompat

//|--------------------|OBJECT - APP SCREEN|---------------------|
object AppScreen {

    //|---------------------|METHOD - SIZE|----------------------|
    fun size(activity: Activity, context: Context): Size {
        //|----------------|VARIABLE SCOPE START|----------------|
        val sizeUnit: Size
        val width: Int
        val height: Int
        //|-----------------|VARIABLE SCOPE END|-----------------|
        //|----------------|CHECK BUILD VERSION|-----------------|
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //|Build.VERSION.SDK_INT >= Build.VERSION_CODES.R|---|
            val defaultDisplay =
                DisplayManagerCompat.getInstance(context).getDisplay(Display.DEFAULT_DISPLAY)
            val displayContext = context.createDisplayContext(defaultDisplay!!)

            width = displayContext.resources.displayMetrics.widthPixels
            height = displayContext.resources.displayMetrics.heightPixels
            sizeUnit = Size(width, height)
        } else {
            //|Build.VERSION.SDK_INT < Build.VERSION_CODES.R|----|
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            height = displayMetrics.heightPixels
            width = displayMetrics.widthPixels
            sizeUnit = Size(width, height)
        }
        return sizeUnit
    }

    //|------------------|METHOD - PX FROM DP|-------------------|
    fun pxFromDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    //|------------------|METHOD - DP FROM PX|-------------------|
    fun dpFromPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    //|-------------------|METHOD - PX TO DP|--------------------|
    fun pxToDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    //|-------------------|METHOD - DP TO PX|--------------------|
    fun dpToPx(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    //|-------------------|DATA CLASS - SIZE|--------------------|
    data class Size(
        var width: Int,
        var height: Int,
    )
}

//|------------------|INT - DP (USAGES 88.DP)|-------------------|
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

//|------------------|INT - PX (USAGES 88.PX)|-------------------|
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
