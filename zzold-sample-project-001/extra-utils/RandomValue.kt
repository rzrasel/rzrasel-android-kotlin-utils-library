package com.rzrasel.rztutorial.utils

import java.util.Random

object RandomValue {
    fun intRange(from: Int, to: Int): Int {
        return Random().nextInt(to - from) + from
    }
}

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) + start

//(0..10).random()
//(0 until 10).random()