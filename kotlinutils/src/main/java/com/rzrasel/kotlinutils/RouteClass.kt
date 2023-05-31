package com.rzrasel.kotlinutils

import android.content.Context

object RouteClass {
    private fun getClassFromName(context: Context, className: String): Class<out Any> {
        //val fullClassName = "${context.packageName}"
        //val fullClassName = "${context.javaClass}"
        val fullPackageClassName = "${context.javaClass.name}"
        val classNameOnly = "${context.javaClass.simpleName}"
        val packagePath = fullPackageClassName.replace(".$classNameOnly", "", false)
        val fullClassName = "${packagePath}.$className"
        //println("DEBUG_LOG_PRINT: getClass $fullClassName")
        return Class.forName("$fullClassName") as Class
    }
    fun<T> getClass(context: Context, className: String): Class<T> {
        return getClassFromName(context, className) as Class<T>
    }
    /*fun getClass(context: Context, className: String): KClass<out Any>? {
        val fullClassName = "${context.javaClass}"
        println("DEBUG_LOG_PRINT: getClass $fullClassName")
        return Class.forName("$fullClassName").kotlin
    }*/
}
/*
val kClass: Class<out Any> = RouteClass.getClass(this@SplashActivity, "DashboardActivity")


val intent = Intent(applicationContext, kClass)
startActivity(intent)
*/