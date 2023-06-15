package com.apphiveme.banglaenglishsmartwordbook.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LessonDataModel(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("bengali")
    var bengali: String? = null,
    @SerializedName("english")
    var english: String? = null,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("big_image_path")
    var bigImagePath: String,
    @SerializedName("small_image_path")
    var smallImagePath: String,
    @SerializedName("audio_path")
    var audioPath: String,
) : Serializable
