package com.rzrasel.rztutorial.repository

import android.content.Context
import com.rzrasel.rztutorial.enumeration.EnumDashboardMenu
import com.rzrasel.rztutorial.model.LessonData
import com.rzrasel.rztutorial.model.LessonWrapperDataModel
import com.rzrasel.rztutorial.network.DataResource

class LessonDataRepository(private val api: LessonData) : BaseRepository() {
    suspend fun getTutorialData(context: Context, enumDashboardMenu: EnumDashboardMenu?) =
        dataApiCall {
            enumDashboardMenu?.let {
                /*when {
                    it.slug
                }*/
                when (it) {
                    EnumDashboardMenu.DAYS_OF_THE_WEEK -> {
                        val data: LessonWrapperDataModel = api.getData(context)
                        data.sevenDay
                    }

                    else -> {
                        DataResource.Failed(null)
                    }
                }
            }
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