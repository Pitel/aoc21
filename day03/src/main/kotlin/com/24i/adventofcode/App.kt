package com.`24i`.adventofcode

val List<Boolean>.mostCommon
    get() = count { it } >= count{ !it }

fun <T> List<List<T>>.transpose(): List<List<T>> = List(first().size) { i ->
    List(size) { j ->
        this[j][i]
    }
}

fun List<Boolean>.toBinaryString() = joinToString("") {
    if (it) {
        1
    } else {
        0
    }.toString()
}

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }
        .mapNotNull {
            try {
                it.map { bit ->
                    bit.digitToInt(2) == 1
                }
            } catch (t: Throwable) {
                null
            }
        }
    val transposed = input.transpose()

    val commonBits = transposed.map { it.mostCommon }
    val gamma = commonBits.joinToString("") {
        if (it) {
            0
        } else {
            1
        }.toString()
    }.toInt(2)
    val epsilon = commonBits.joinToString("") {
        if (it) {
            1
        } else {
            0
        }.toString()
    }.toInt(2)
    println(gamma * epsilon)

    val o2 = transposed.foldIndexed(input) { i, acc, _ ->
        if (acc.size == 1) {
            acc
        } else {
            val accMostCommon = acc.transpose()[i].mostCommon
            acc.filter {
                it[i] == accMostCommon
            }
        }
    }.first().toBinaryString().toInt(2)
    val co2 = transposed.foldIndexed(input) { i, acc, _ ->
        if (acc.size == 1) {
            acc
        } else {
            val accMostCommon = !acc.transpose()[i].mostCommon
            acc.filter {
                it[i] == accMostCommon
            }
        }
    }.first().toBinaryString().toInt(2)
    println(o2 * co2)
}