package com.rzrasel.kotlinutils.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

//|--------------------|OBJECT PRO RESOURCES|--------------------|
object ProResources {

    //|-----------------|METHOD - GET DRAWABLE|------------------|
    fun getDrawable(context: Context, drawableName: String?): Drawable? {
        val drawableResourceId: Int =
            context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.packageName
            )
        //context.applicationInfo.packageName
        return ContextCompat.getDrawable(context, drawableResourceId)
    }

    //|----------------|METHOD - GET DRAWABLE ID|----------------|
    fun getDrawableId(context: Context, drawableName: String?): Int {
        return context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.packageName
            )
        //context.applicationInfo.packageName
    }

    //|----------------|METHOD - GET RESOURCE ID|----------------|
    fun getResourceId(resourceName: String, resourceType: String?, context: Context): Int {
        val resourceId = context.resources.getIdentifier(
            resourceName, resourceType,
            context.packageName
        )
        return if (resourceId == 0) {
            throw IllegalArgumentException(
                "No resource string found with name $resourceName"
            )
        } else {
            resourceId
        }
    }

    //|-----------------|METHOD - GET DRAWABLE|------------------|
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