package com.rzrasel.kotlinadmob.utils

object AdConstants {
    object AdMob {
        private val adProperty: TestProperty = TestProperty
        //
        private const val minTime: Long = adProperty.minTime
        private const val maxTime: Long = adProperty.maxTime
        fun getTimeDuration(): Long {
            val min: Long = minTime * 1000
            val max: Long = maxTime * 1000
            return (min..max).random()
        }

        private const val minEvent: Int = adProperty.minEvent
        private const val maxEvent: Int = adProperty.maxEvent
        fun getTargetTotalEvent(): Int = (minEvent..maxEvent).random()

        private object TestProperty {
            const val minTime: Long = (1 * 60).toLong()
            const val maxTime: Long = (1.5 * 60).toLong()
            const val minEvent: Int = 4
            const val maxEvent: Int = 8
        }

        private object ReleaseProperty {
            const val minTime: Long = (3 * 60).toLong()
            const val maxTime: Long = (4.5 * 60).toLong()
            const val minEvent: Int = 25
            const val maxEvent: Int = 40
        }
    }
}