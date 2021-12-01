package com.`24i`.adventofcode

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .mapNotNull {
            try {
                it.toUInt()
            } catch (t: Throwable) {
                null
            }
        }

    val increases = input.foldIndexed(0u) { i, acc, depth ->
        if (i == 0) {
            acc
        } else {
            if (input[i - 1] < depth) {
                acc.inc()
            } else {
                acc
            }
        }
    }
    println(increases)

    val windowed = input
        .mapIndexedNotNull { i, _ ->
            if (i > input.size - 3) {
                null
            } else {
                input.subList(i, i + 3).sum()
            }
        }
    val windowIncreases = windowed
        .foldIndexed(0u) { i, acc, depth ->
            if (i == 0) {
                acc
            } else {
                if (windowed[i - 1] < depth) {
                    acc.inc()
                } else {
                    acc
                }
            }
        }
    println(windowIncreases)
}