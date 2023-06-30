package com.rzrasel.kotlinutils.logger

import android.content.Context
import android.widget.Toast

object ShowToast {
    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun String.showToast(context: Context) =
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()