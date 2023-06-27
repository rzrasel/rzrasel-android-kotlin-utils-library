package com.rzrasel.kotlinutils.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

// File Name: SerializableExtension

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

/*
data class DataModel(
    val id: Int
): Serializable

fun getExampleBundle() {
    var dataModel: DataModel
    val bundle: Bundle? = intent.extras
    bundle?.let { itBundle ->
        itBundle.serializable<DataModel>("bundle_key").let {
            repositoryItemModel = it!!
        }
    }
}

fun getExampleIntent() {
    var dataModel: DataModel
    intent.serializable<DataModel>("intent_key").let {
        dataModel = it!!
    }
}

*/