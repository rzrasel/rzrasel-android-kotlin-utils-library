package com.rzrasel.appusages

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rzrasel.kotlinutils.AppScreen
import com.rzrasel.kotlinutils.dp
import com.rzrasel.kotlinutils.px

//|-------------------|CLASS - MAIN ACTIVITY|--------------------|
class MainActivity : AppCompatActivity() {
    //|---------------|CLASS VARIABLE SCOPE START|---------------|
    private lateinit var activity: Activity
    private lateinit var context: Context
    //|----------------|CLASS VARIABLE SCOPE END|----------------|

    //|-------------------|CLASS CONSTRUCTOR|--------------------|
    //|Method - On Create|---------------------------------------|
    override fun onCreate(savedInstanceState: Bundle?) {
        //|-----------|SYSTEM DEFINED PROPERTY START|------------|
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //|------------|SYSTEM DEFINED PROPERTY END|-------------|
        //|---------------|USER DEFINED PROPERTY|----------------|
        activity = this@MainActivity
        context = this@MainActivity

        //|-------------|USAGES - APP SCREEN CLASS|--------------|
        onAppScreen()
    }
    private fun onAppScreen() {
        //|-------------|USAGES - APP SCREEN CLASS|--------------|
        val size: AppScreen.Size = AppScreen.size(activity, context)
        val width: Int = size.width
        val height: Int = size.height
        println("DEBUG_LOG_PRINT: size width $width")
        println("DEBUG_LOG_PRINT: size height $height")
        println("DEBUG_LOG_PRINT: 100.dp ${100.dp}")
        println("DEBUG_LOG_PRINT: 100.px ${100.px}")
    }
}