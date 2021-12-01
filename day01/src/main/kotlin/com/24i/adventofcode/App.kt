package com.`24i`.adventofcode

val List<UInt>.increases
    get() = foldIndexed(0u) { i, acc, depth ->
        if (i == 0) {
            acc
        } else {
            if (this[i - 1] < depth) {
                acc.inc()
            } else {
                acc
            }
        }
    }

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

    println(input.increases)
    println(input.windowed(3).map { it.sum() }.increases)
}