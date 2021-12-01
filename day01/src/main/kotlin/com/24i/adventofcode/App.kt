package com.`24i`.adventofcode

val List<UInt>.increases
    get() = windowed(2).count { (a, b) -> a < b }

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