package com.rzrasel.kotlinutils.extension

import android.annotation.SuppressLint
import android.content.Context

fun Int.getString(context: Context): String {
    return context.resources.getString(this)
}

@SuppressLint("DiscouragedApi")
fun String.getString(context: Context): String {
    val resourceId = context.resources.getIdentifier(this, "string", context.packageName)
    return context.resources.getString(resourceId)
}