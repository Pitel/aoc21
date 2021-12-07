package com.`24i`.adventofcode

import kotlin.math.abs

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .first()
        .split(',')
        .mapNotNull { it.toUShortOrNull() }

    var fuels = List(input.maxOrNull()!!.toInt()) { i ->
        input.sumOf {
            abs(it.toShort() - i)
        }.toUInt()
    }
    println(fuels.minOrNull())

    fuels = List(input.maxOrNull()!!.toInt()) { i ->
        input.sumOf { pos ->
            (0..abs(i - pos.toShort())).sum()
        }.toUInt()
    }
    println(fuels.minOrNull())
}