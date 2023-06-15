package com.apphiveme.banglaenglishsmartwordbook.model

import android.content.Context
import com.apphiveme.banglaenglishsmartwordbook.helper.ProGson
import com.apphiveme.banglaenglishsmartwordbook.utils.AppConstants
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.rzrasel.kotlinutils.ReadJsonFile
import java.io.Serializable
import java.lang.reflect.Type

data class LessonWrapperDataModel(
    var dataModel: Map<String, ArrayList<LessonDataModel>>
): Serializable

object LessonData {
    fun getData(context: Context): LessonWrapperDataModel {
        val gson = GsonBuilder().create()
        val jsonTop = ReadJsonFile.fromJson(context, AppConstants.Assets.Json.jsonLessonDataModel)
        val typeTop: Type = object : TypeToken<Map<String, ArrayList<LessonDataModel>>>() {}.type
        val dataMap: Map<String, ArrayList<LessonDataModel>> = gson.fromJson(jsonTop, typeTop)
        //println("DEBUG_LOG_PRINT: getData json dataMap ${dataMap.toString()}")
        return LessonWrapperDataModel(dataMap)
    }
    fun getDataOld01(context: Context): LessonWrapperDataModel {
        //val dataList = ArrayList<DashboardMenuModel>()
        //dataList.add(DashboardMenuModel(R.drawable.online, "Title 1", ""))
        //dataList.add(DashboardMenuModel("Title 2"))
        val json = ReadJsonFile.fromJson(context, AppConstants.Assets.Json.jsonLessonDataModel)
        //return ProGson.fromJson<ArrayList<DashboardMenuModel>>(json) as ArrayList<DashboardMenuModel>
        //
        val type = object : TypeToken<LessonWrapperDataModel>() {}.type
        return ProGson.parseArray<LessonWrapperDataModel>(
            json = json,
            typeToken = type
        ) as LessonWrapperDataModel
    }
}

data class LessonWrapperDataModelOld01(
    @SerializedName("days_of_the_week")
    var daysOfTheWeek: ArrayList<LessonDataModel> = arrayListOf(),
    @SerializedName("months_of_the_year")
    var monthsOfTheYear: ArrayList<LessonDataModel> = arrayListOf(),
    @SerializedName("human_body_part")
    var humanBodyPart: ArrayList<LessonDataModel> = arrayListOf(),
): Serializable