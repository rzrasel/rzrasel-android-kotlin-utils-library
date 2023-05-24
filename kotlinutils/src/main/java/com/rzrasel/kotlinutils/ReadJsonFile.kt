package com.rzrasel.kotlinutils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException

//|-------------------|OBJECT READ JSON FILE|--------------------|
object ReadJsonFile {

    //|----------------|METHOD - GET ASSETS JSON|----------------|
    private fun getAssetsJson(context: Context, fileName: String): String {

        lateinit var jsonString: String
        try {
            //jsonString = context.assets.open("country/country.json")
            /*jsonString = context.assets.open(filePath)
                .bufferedReader()
                .use { it.readText() }*/
            val bufferReader = context.assets.open(fileName).bufferedReader()
            jsonString = bufferReader.use {
                it.readText()
            }
        } catch (ioException: IOException) {
            //AppLogger.d(ioException)
        }

        /*val listCountryType = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)*/
        return jsonString
    }

    //|-------------------|METHOD - FROM JSON|-------------------|
    fun fromJson(context: Context, fileName: String): String {
        /*withContext(Dispatchers.Default) {
            async {
                return getAssetsJson(context, fileName)
            }
        }*/
        return getAssetsJson(context, fileName)
    }
}

/*
Read JSON file from assets
https://sazib.medium.com/read-json-file-from-assets-346f624faf92
https://www.bezkoder.com/kotlin-android-read-json-file-assets-gson/

https://stackoverflow.com/questions/60727973/generic-type-t-is-not-recognized-by-gson-in-kotlin
https://isamatov.com/handle-generics-using-gson-kotlin-java/

https://www.tutorialsbuzz.com/2019/08/android-json-parsing-assets-kotlin.html

https://rrtutors.com/tutorials/How-do-I-Read-JSON-file-From-Assets-Folder-Android-Kotlin
https://www.section.io/engineering-education/prefilling-room-database-with-json-data-in-android/
*/