package com.rzrasel.kotlinutils

//|-----------------|CLASS - DELAY BIT PATTERN|------------------|
object DelayBitPattern {

    //|----------------|METHOD - CURRENT MILLIS|-----------------|
    fun currentMillis() = System.currentTimeMillis()

    //|-------------------|METHOD - IS PASSED|-------------------|
    fun isPassed(startTime: Long, endTime: Long, targetMillis: Long): Boolean {
        val duration: Long = endTime - startTime
        println("DEBUG_LOG_PRINT: duration $duration")
        println("DEBUG_LOG_PRINT: targetMillis $targetMillis")
        if (duration > targetMillis) {
            return true
        }
        return false
    }

    //|------------------|METHOD - GET MILLIS|-------------------|
    fun getMillis(hour: Int = 0, minute: Int = 0, second: Int): Long {
        return (hour * 60 * 60 + minute * 60 + second) * 1000L
    }
}