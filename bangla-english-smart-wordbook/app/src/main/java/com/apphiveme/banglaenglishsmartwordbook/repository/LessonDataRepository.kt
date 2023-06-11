package com.apphiveme.banglaenglishsmartwordbook.repository

import android.content.Context
import com.apphiveme.banglaenglishsmartwordbook.enumeration.EnumDashboardMenu
import com.apphiveme.banglaenglishsmartwordbook.model.LessonData
import com.apphiveme.banglaenglishsmartwordbook.model.LessonWrapperDataModel
import com.apphiveme.banglaenglishsmartwordbook.network.DataResource

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
                        data.daysOfTheWeek
                    }
                    EnumDashboardMenu.MONTHS_OF_THE_YEAR -> {
                        val data: LessonWrapperDataModel = api.getData(context)
                        data.monthsOfTheYear
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