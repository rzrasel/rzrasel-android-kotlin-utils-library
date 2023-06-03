package com.rzrasel.rztutorial.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.rzrasel.kotlinutils.ReadJsonFile
import com.rzrasel.rztutorial.helper.ProGson

data class DashboardMenuModel(
    @SerializedName("image")
    var image: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("slug")
    var slug: String?,
)

object DashboardMenuData {
    fun getData(context: Context): ArrayList<DashboardMenuModel> {
        //val dataList = ArrayList<DashboardMenuModel>()
        //dataList.add(DashboardMenuModel(R.drawable.online, "Title 1", ""))
        //dataList.add(DashboardMenuModel("Title 2"))
        val json = ReadJsonFile.fromJson(context, "test.json")
        //return ProGson.fromJson<ArrayList<DashboardMenuModel>>(json) as ArrayList<DashboardMenuModel>
        //
        val type = object : TypeToken<List<DashboardMenuModel>>() {}.type
        return ProGson.parseArray<List<DashboardMenuModel>>(
            json = json,
            typeToken = type
        ) as ArrayList<DashboardMenuModel>
    }
}
//https://www.veed.io/
//https://editor.audio/
//https://audiotoolset.com/editor
/*
<?php
$str = "the Untold history of manusCript";
/*//Not working
//$spaced = preg_replace("/^([a-z]*)[A-Z].*$/", "~$1", $str);
//Replace all upper case character array
$spaced = preg_replace("/([A-Z])/", "-$1", $str);
//Find all upper case character array
preg_match_all("/[A-Z]/", $str, $matches, PREG_OFFSET_CAPTURE);
//print_r($matches[0]);*/

/*//Find all word starting with uppercase in a string in php
$spaced = preg_match_all("/\b([a-z]+)\b/", $str, $matches);
$spaced = preg_match_all("/\b[A-Z]+([a-z]+)/", $str, $matches);
$spaced = preg_match_all("/\b[A-Z]([a-z]*)/", $str, $matches);
echo $matches[0];
print_r($matches[0]);*/

//$spaced = preg_match_all("/\b[^A-Z]([a-z]*)/", $str, $matches);
preg_match_all("/([A-Z]+)/", $str, $matches);
//echo $matches[0];
print_r($matches[0]);
preg_match_all("/\b[A-Z].([a-z]*)/", $str, $matches);
preg_match_all("/\b[A-Za-z]+\b/", $str, $matches);
preg_match_all("/\b(?=.*[A-Z])(?=.*[a-z])[A-Za-z]+\b/", $str, $matches);
print_r($matches[0]);
$spaced = preg_match_all("/\b([a-z]+)\b/", $str, $matches);
//echo $matches[0];
print_r($matches[0]);
?>



*/