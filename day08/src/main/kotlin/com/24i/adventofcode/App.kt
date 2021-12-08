package com.`24i`.adventofcode

import kotlinx.coroutines.*

suspend fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { it.split(" | ") }
        .map {
            it[0].split(' ') to it[1].split(' ')
        }

    val digits = arrayOf(
        "abcefg",
        "cf",
        "acdeg",
        "acdfg",
        "bcdf",
        "abdfg",
        "abdefg",
        "acf",
        "abcdefg",
        "abcdfg"
    )

    val unique = digits
        .groupBy { it.length }
        .filterValues { it.size == 1 }
        .keys
    println(
        input.sumOf { line ->
            line.second.count { segment ->
                segment.length in unique
            }
        }
    )

    println(
        input.sumOf { line ->
            val key: String = try {
                coroutineScope {
                    while (isActive) {
                        launch {
                            val mapping = digits[8].toCharArray().copyOf().apply { shuffle() }
                                .zip(digits[8].toCharArray())
                                .toMap()
                            if (
                                line.first.all { segments ->
                                    segments.toCharArray().map { mapping[it]!! }.sorted()
                                        .joinToString("") in digits
                                }
                            ) {
                                this@coroutineScope.cancel(mapping.keys.joinToString(""))
                            }
                        }
                    }
                    ""
                }
            } catch (e: CancellationException) {
                e.message!!
            }
            val mapping = key.toCharArray()
                .zip(digits[8].toCharArray())
                .toMap()
            line.second
                .map { segments ->
                    digits.indexOf(
                        segments.toCharArray().map { mapping[it]!! }.sorted().joinToString("")
                    )
                }
                .joinToString("")
                .toULong()
        }
    )
}