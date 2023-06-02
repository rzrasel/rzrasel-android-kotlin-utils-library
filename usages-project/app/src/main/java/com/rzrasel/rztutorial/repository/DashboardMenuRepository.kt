package com.rzrasel.rztutorial.repository

import android.content.Context
import com.rzrasel.rztutorial.model.DashboardMenuData
import com.rzrasel.rztutorial.model.DashboardMenuModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

/*class DashboardMenuRepository {
    fun getMenuData(): ArrayList<DashboardMenuModel> {
        return DashboardMenuData.getData()
    }
}*/
class DashboardMenuRepository(private val api: DashboardMenuData) : BaseRepository() {
    suspend fun getMenuData(context: Context) = dataApiCall {
        api.getData(context)
    }
    /*fun getMenuData(context: Context) = flow {
        val response = api.getData(context)
        emit(response)
    }*/
    /*fun getMenuData(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            suspendCoroutine<Unit> {
                dataApiCall {
                    api.getData(context)
                }
            }
        }
    }*/
}
