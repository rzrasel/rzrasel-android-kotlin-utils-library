package com.apphiveme.banglaenglishsmartwordbook.network

sealed class DataResource<out T> {
    data class Success<out T>(val value: T) : DataResource<T>()
    data class Failed<out T>(val value: T) : DataResource<T>()
}
