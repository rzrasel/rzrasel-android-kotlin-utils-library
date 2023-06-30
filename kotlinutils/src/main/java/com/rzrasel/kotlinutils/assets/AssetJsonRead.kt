package com.rzrasel.kotlinutils.assets

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

//|-------------------|OBJECT READ JSON FILE|--------------------|
object AssetJsonRead {

    //|----------------|METHOD - GET ASSETS JSON|----------------|
    private suspend fun getAssetsJson(context: Context, fileName: String): String? =
        withContext(Dispatchers.IO) {
            try {
                val bufferReader = context.assets.open(fileName).bufferedReader()
                bufferReader.use {
                    it.readText()
                }
            } catch (ex: IOException) {
                null
            }
        }

    //|-------------------|METHOD - FROM JSON|-------------------|
    suspend fun fromJson(context: Context, fileName: String): String? =
        getAssetsJson(context, fileName)
    /*suspend fun fromJson(context: Context, fileName: String): String? {
        *//*return withContext(Dispatchers.IO) {
            /=*async {
                return@async getAssetsJson(context, fileName)
            }*=/
            getAssetsJson(context, fileName)
        }*//*
        return getAssetsJson(context, fileName)
    }*/

    /*private fun getAssetsJsonOld02(context: Context, fileName: String): String? {
        var jsonString: String? = null
        return try {
            val bufferReader = context.assets.open(fileName).bufferedReader()
            bufferReader.use {
                it.readText()
            }
        } catch (ex: IOException) {
            null
        }
        /=*try {
            withContext(Dispatchers.IO) {
                async {
                    val bufferReader = context.assets.open(fileName).bufferedReader()
                    jsonString = bufferReader.use {
                        it.readText()
                    }
                    return@async jsonString
                }.await()
            }
            return jsonString
        } catch (ex: IOException) {
            return jsonString
        }*=/
        /=*withContext(Dispatchers.IO) {
            try {
                async {
                    val bufferReader = context.assets.open(fileName).bufferedReader()
                    jsonString = bufferReader.use {
                        it.readText()
                    }
                    return@async jsonString
                }.await()
            } catch (ex: IOException) {
                return@withContext jsonString
            }
        }*=/
    }*/

    /*private suspend fun getAssetsJsonOld01(context: Context, fileName: String): String {

        var jsonString: String
        try {
            //jsonString = context.assets.open("country/country.json")
            /=*jsonString = context.assets.open(filePath)
                .bufferedReader()
                .use { it.readText() }*=/
            val bufferReader = context.assets.open(fileName).bufferedReader()
            jsonString = bufferReader.use {
                it.readText()
            }
        } catch (ioException: IOException) {
            //AppLogger.d(ioException)
        }

        /=*val listCountryType = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)*=/
        return jsonString
    }*/
}

/*fun Context.readTextFromAsset(fileName : String) : String{
    return assets.open(fileName).bufferedReader().use {
        it.readText()}
}*/

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