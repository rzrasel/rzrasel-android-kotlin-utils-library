package com.rzrasel.kotlinutils.helper

import android.app.Activity
import android.os.Build
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

/*
data class DataModel(
    val id: Int
): Serializable

fun getExampleBundle() {
    val bundle: Bundle? = intent.extras
    bundle?.let {
        bundle.apply {
            dataModel = SerializableHelper.getSerializable(
                activity,
                AppConstants.AppDefined.KeyValue.intentMenuModel,
                DataModel::class.java
            )
        }
    }
}
*/