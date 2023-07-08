package com.rzrasel.kotlinadmob.utils

object AdConstants {
    private val runType: RunType = RunType.DEBUG

    object AdMob {

        private val adProperty: PropertyModel = if (runType == RunType.RELEASE) {
            ReleaseProperty.property
        } else {
            DebugProperty.property
        }

        //
        private val minTime: Long = adProperty.minTime
        private val maxTime: Long = adProperty.maxTime
        fun getTimeDuration(): Long {
            val min: Long = minTime * 1000
            val max: Long = maxTime * 1000
            return (min..max).random()
        }

        private val minEvent: Int = adProperty.minEvent
        private val maxEvent: Int = adProperty.maxEvent
        fun getTargetTotalEvent(): Int = (minEvent..maxEvent).random()

        private object DebugProperty {
            const val minTime: Long = (2 * 60).toLong()
            const val maxTime: Long = (2.5 * 60).toLong()
            const val minEvent: Int = 10
            const val maxEvent: Int = 15
            val property: PropertyModel =
                PropertyModel(
                    minTime,
                    maxTime,
                    minEvent,
                    maxEvent
                )
        }

        private object ReleaseProperty {
            private const val minTime: Long = (3 * 60).toLong()
            private const val maxTime: Long = (4.5 * 60).toLong()
            private const val minEvent: Int = 25
            private const val maxEvent: Int = 40
            val property: PropertyModel =
                PropertyModel(
                    minTime,
                    maxTime,
                    minEvent,
                    maxEvent
                )
        }

        private data class PropertyModel(
            val minTime: Long,
            val maxTime: Long,
            val minEvent: Int,
            val maxEvent: Int
        )
    }

    enum class RunType() {
        DEBUG,
        RELEASE;
    }
}