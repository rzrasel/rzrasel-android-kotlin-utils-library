package com.rzrasel.kotlinutils.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.Toast

class ResourceUtility {
    fun isDebuggable(activity: Activity): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            activity.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } else {
            return false
        }
    }

    fun isDebuggable(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } else {
            return false
        }
    }
}

fun Activity.isDebuggable(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
        applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } else {
        return false
    }
}

fun Context.isDebuggable(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
        applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } else {
        return false
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.singleClickable(delayMillis: Long = 2000L) {
    isClickable = !isClickable
    postDelayed({
        isClickable = true
    }, delayMillis)
}

fun View.singleClickable(canClick: Boolean, readyToClick: (isClickable: Boolean) -> Boolean) {
    /*var canClick = readyToClick(!isClickable)
    println("DEBUG_LOG_PRINT: singleClickable isClickable $canClick")*/
    //val canClick: Boolean = isClickable
    var clickable: Boolean = canClick
    println("DEBUG_LOG_PRINT: singleClickable isClickable $clickable")
    if (clickable) {
        clickable = readyToClick(clickable)
        //clickable = false
        postDelayed({
            clickable = true
        }, 10000)
    }
}

fun Button.singleClickable(delayMillis: Long = 2000L) {
    isClickable = !isClickable
    postDelayed({
        isClickable = true
    }, delayMillis)
}

fun Button.singleClickable(
    delayMillis: Long = 2000L,
    isClickable: (isClickable: Boolean) -> Unit
) {

}