package com.rzrasel.rztutorial.helper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ProGson {
    //fun <T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)
    fun <T> fromJson(json: String): T {
        val gson = Gson()
        val typeToken = object : TypeToken<T>() {}.type
        return gson.fromJson(json, typeToken)
    }

    fun <T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }
}

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)