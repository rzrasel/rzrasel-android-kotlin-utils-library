package com.rzrasel.rztutorial.enumeration

import android.util.Log

enum class EnumDashboardMenu {
    DAYS_OF_THE_WEEK("days_of_the_week", 1),
    HUMAN_BODY_PARTS("human_body_parts", 2),
    NONE("none", -1);

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
        fun findSlug(value: String): EnumDashboardMenu? = EnumDashboardMenu.values().find { it.slug == value }
    }
}