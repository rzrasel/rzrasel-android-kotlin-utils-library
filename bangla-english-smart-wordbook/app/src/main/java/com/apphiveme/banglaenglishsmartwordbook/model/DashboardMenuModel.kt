package com.apphiveme.banglaenglishsmartwordbook.model

import android.content.Context
import com.apphiveme.banglaenglishsmartwordbook.helper.ProGson
import com.apphiveme.banglaenglishsmartwordbook.utils.AppConstants
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.rzrasel.kotlinutils.ReadJsonFile
import java.io.Serializable

data class DashboardMenuModel(
    @SerializedName("bn_image_path")
    var bnImagePath: String,
    @SerializedName("en_image_path")
    var enImagePath: String,
    @SerializedName("bn_title")
    var bnTitle: String,
    @SerializedName("en_title")
    var enTitle: String,
    @SerializedName("bn_description")
    var bnDescription: String?,
    @SerializedName("en_description")
    var enDescription: String?,
    @SerializedName("slug")
    var slug: String,
): Serializable

object DashboardMenuData {
    fun getData(context: Context): ArrayList<DashboardMenuModel> {
        //val dataList = ArrayList<DashboardMenuModel>()
        //dataList.add(DashboardMenuModel(R.drawable.online, "Title 1", ""))
        //dataList.add(DashboardMenuModel("Title 2"))
        val json = ReadJsonFile.fromJson(context, AppConstants.Assets.Json.jsonDashboardMenuBn)
        //return ProGson.fromJson<ArrayList<DashboardMenuModel>>(json) as ArrayList<DashboardMenuModel>
        //
        val type = object : TypeToken<List<DashboardMenuModel>>() {}.type
        return ProGson.parseArray<List<DashboardMenuModel>>(
            json = json,
            typeToken = type
        ) as ArrayList<DashboardMenuModel>
    }
}