package com.rzrasel.rztutorial.repository

import com.rzrasel.rztutorial.network.DataResource

abstract class BaseRepository {
    fun <T> dataApiCall(apiCall: () -> T): DataResource<T> {
        return DataResource.Success(apiCall.invoke())
    }
}