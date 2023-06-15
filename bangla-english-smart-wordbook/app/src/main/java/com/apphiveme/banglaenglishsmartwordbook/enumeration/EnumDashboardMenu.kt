package com.apphiveme.banglaenglishsmartwordbook.enumeration

import android.util.Log

enum class EnumDashboardMenu(slug: String, serial: Int) {
    DAYS_OF_THE_WEEK("days_of_the_week", 1),
    MONTHS_OF_THE_YEAR("months_of_the_year", 2),
    SIX_SEASON("six_season", 3),
    HUMAN_BODY_PARTS("human_body_parts", -3),
    NONE("none", -1);

    var slug: String? = slug
    var serial: Int? = serial

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