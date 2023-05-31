package com.rzrasel.kotlinutils

import java.util.Random

//|--------------------|OBJECT RANDOM VALUE|---------------------|
object RandomValue {

    //|-------------------|METHOD - INT RANGE|-------------------|
    fun intRange(from: Int, to: Int): Int {
        return Random().nextInt(to - from) + from
    }
}

//|---------|RANDOM INT RANGE (USAGES (0..10).RANDOM())|---------|
//|RANDOM INT RANGE (USAGES (0 UNTIL 10).RANDOM())|--------------|
fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) + start