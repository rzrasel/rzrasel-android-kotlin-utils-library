package com.apphiveme.banglaenglishsmartwordbook.repository

import com.apphiveme.banglaenglishsmartwordbook.network.DataResource


abstract class BaseRepository {
    fun <T> dataApiCall(apiCall: () -> T): DataResource<T> {
        return DataResource.Success(apiCall.invoke())
    }
}