package com.`24i`.adventofcode

val List<UInt>.increases
    get() = windowed(2).count { (a, b) -> a < b }

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .mapNotNull {
            try {
                it.split(' ')
            } catch (t: Throwable) {
                null
            }
        }
        .mapNotNull {
            try {
                Pair(it.first(), it.last().toUInt())
            } catch (t: Throwable) {
                null
            }
        }

    println(
        input.fold(Pair(0u, 0u)) { acc, action ->
            when(action.first) {
                "forward" -> Pair(acc.first + action.second, acc.second)
                "down" -> Pair(acc.first, acc.second + action.second)
                "up" -> Pair(acc.first, acc.second - action.second)
                else -> acc
            }
        }.let { it.first * it.second }
    )
    println(
        input.fold(Triple(0u, 0u, 0u)) { acc, action ->
            when(action.first) {
                "forward" -> Triple(
                    acc.first + action.second,
                    acc.second + acc.third * action.second,
                    acc.third
                )
                "down" -> Triple(
                    acc.first,
                    acc.second,
                    acc.third + action.second
                )
                "up" -> Triple(
                    acc.first,
                    acc.second,
                    acc.third - action.second
                )
                else -> acc
            }
        }.let { it.first * it.second }
    )
}