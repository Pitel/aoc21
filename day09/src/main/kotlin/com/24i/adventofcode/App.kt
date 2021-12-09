package com.`24i`.adventofcode

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

suspend fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            line.map {
                it.digitToInt().toUByte()
            }
        }

    val lows = withContext(Dispatchers.Default) {
        input
            .mapIndexed { i, line ->
                line.mapIndexed { j, risk ->
                    async {
                        val adjacent = listOfNotNull(
                            input.getOrNull(i)?.getOrNull(j - 1),
                            input.getOrNull(i)?.getOrNull(j + 1),
                            input.getOrNull(i + 1)?.getOrNull(j),
                            input.getOrNull(i - 1)?.getOrNull(j),
                        )
                        if (risk < adjacent.minOrNull()!!) {
                            i to j
                        } else {
                            null
                        }
                    }
                }
            }
            .flatten()
            .awaitAll()
            .filterNotNull()
        //.also { println(it) }
    }

    println(
        lows.sumOf { (i, j) ->
            input[i][j].inc().toUInt()
        }
    )

    println(
        withContext(Dispatchers.Default) {
            lows
                .map {
                    async {
                        val basin = mutableSetOf(it)
                        val candidates = listOf(
                            it.first to it.second + 1,
                            it.first to it.second - 1,
                            it.first + 1 to it.second,
                            it.first - 1 to it.second,
                        ).filter { (i, j) ->
                            input.getOrElse(i) { emptyList() }.getOrElse(j) { 9u } < 9u
                        }.toMutableSet()
                        while (candidates.isNotEmpty()) {
                            val candidate = candidates.first()
                            candidates += candidate.first to candidate.second + 1
                            candidates += candidate.first to candidate.second - 1
                            candidates += candidate.first + 1 to candidate.second
                            candidates += candidate.first - 1 to candidate.second
                            basin += candidate
                            candidates.removeAll(basin)
                            candidates.removeAll { (i, j) ->
                                input.getOrElse(i) { emptyList() }.getOrElse(j) { 9u } >= 9u
                            }
                        }
                        basin.size
                    }
                }
                .awaitAll()
                .sortedDescending()
                .take(3)
                .reduce { acc, size ->
                    acc * size
                }
        }
    )
}