package com.rzrasel.rztutorial.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.rzrasel.kotlinutils.ReadJsonFile
import com.rzrasel.rztutorial.helper.ProGson
import com.rzrasel.rztutorial.utils.AppConstants
import java.io.Serializable

data class LessonWrapperDataModel(
    @SerializedName("human_body_part")
    var humanBodyPart: ArrayList<TutorialDataModel> = arrayListOf(),
    @SerializedName("seven_day")
    var sevenDay: ArrayList<TutorialDataModel> = arrayListOf(),
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