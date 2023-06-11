package com.apphiveme.banglaenglishsmartwordbook.model

import android.content.Context
import com.apphiveme.banglaenglishsmartwordbook.helper.ProGson
import com.apphiveme.banglaenglishsmartwordbook.utils.AppConstants
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.rzrasel.kotlinutils.ReadJsonFile
import java.io.Serializable

data class LessonWrapperDataModel(
    @SerializedName("human_body_part")
    var humanBodyPart: ArrayList<LessonDataModel> = arrayListOf(),
    @SerializedName("seven_day")
    var sevenDay: ArrayList<LessonDataModel> = arrayListOf(),
): Serializable

object LessonData {
    fun getData(context: Context): LessonWrapperDataModel {
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