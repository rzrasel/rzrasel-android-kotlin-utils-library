package com.rzrasel.kotlinutils

import android.content.Context
import android.widget.Toast

object ShowToast {
    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}