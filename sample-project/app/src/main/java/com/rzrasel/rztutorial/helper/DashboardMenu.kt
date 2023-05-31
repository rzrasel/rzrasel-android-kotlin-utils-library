package com.rzrasel.rztutorial.helper

import android.util.Log

enum class DashboardMenu {
    SWORD("Sword", 12);

    var slug: String? = null
    var id: Int? = null

    constructor(slug: String, id: Int) {
        this.slug = slug
        this.id = id
    }

    companion object {
        /*fun fromValue(value: String) {
            values().forEach {
                val dashboardMenu = it as DashboardMenu
                Log.d("LOG", "DEBUG_LOG_PRINT: ${it.slug}")
            }
        }*/
        fun find(value: String): DashboardMenu? = DashboardMenu.values().find { it.slug == value }
    }
}