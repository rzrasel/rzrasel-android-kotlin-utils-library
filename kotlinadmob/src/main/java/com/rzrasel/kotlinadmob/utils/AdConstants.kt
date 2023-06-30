package com.rzrasel.kotlinadmob.utils

object AdConstants {
    object AdMob {
        private const val minTime: Long = (3 * 60).toLong()
        private const val maxTime: Long = (4.5 * 60).toLong()
        fun getTimeDuration(): Long {
            val min: Long = minTime * 1000
            val max: Long = maxTime * 1000
            return (min..max).random()
        }

        fun getTargetTotalEvent(): Int = (25..40).random()
    }
}