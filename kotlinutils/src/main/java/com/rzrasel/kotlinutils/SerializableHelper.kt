package com.rzrasel.kotlinutils

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

object SerializableHelper {
    fun <T : Serializable?> getSerializable(
        activity: Activity,
        key: String,
        clazz: Class<T>
    ): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(key, clazz)!!
        else
            @Suppress("DEPRECATION") activity.intent.getSerializableExtra(key) as T
    }
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
        key,
        T::class.java
    )

    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}