package com.rzrasel.rztutorial.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


object ProResources {
    fun getDrawable(context: Context, drawableName: String?): Drawable? {
        val drawableResourceId: Int =
            context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.applicationInfo.packageName
            )
        return ContextCompat.getDrawable(context, drawableResourceId)
    }

    fun getDrawableId(context: Context, drawableName: String?): Int {
        return context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.applicationInfo.packageName
            )
    }

    fun getResourceId(resourceName: String, resourceType: String?, context: Context): Int {
        val resourceId = context.resources.getIdentifier(
            resourceName, resourceType,
            context.applicationInfo.packageName
        )
        return if (resourceId == 0) {
            throw IllegalArgumentException(
                "No resource string found with name $resourceName"
            )
        } else {
            resourceId
        }
    }

    /*fun getDrawable(context: Context, drawableName: String?): Drawable? {
        return context.resources.getDrawable(
            context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.packageName
            )
        )
    }*/
}