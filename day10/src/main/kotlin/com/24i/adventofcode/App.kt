package com.`24i`.adventofcode

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

suspend fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }

    val opening = "([{<"
    val closing = ")]}>"

    val score = withContext(Dispatchers.Default) {
        input.map { line ->
            async {
                val stack = mutableListOf<Char>()
                var illegal: Char? = null
                line.forEach { bracket ->
                    if (bracket in opening) {
                        stack += bracket
                    } else {
                        if (stack.removeLast() != opening[closing.indexOf(bracket)]) {
                            illegal = bracket
                            return@forEach
                        }
                    }
                }
                when (illegal) {
                    ')' -> 3
                    ']' -> 57
                    '}' -> 1197
                    '>' -> 25137
                    else -> 0
                }.toUInt()
            }
        }
            .awaitAll()
            .sum()
    }
    println(score)

    val autocompletes = withContext(Dispatchers.Default) {
        input.map { line ->
            async {
                val stack = mutableListOf<Char>()
                line.forEach { bracket ->
                    if (bracket in opening) {
                        stack += bracket
                    } else {
                        if (stack.removeLast() != opening[closing.indexOf(bracket)]) {
                            return@async 0u
                        }
                    }
                }
                stack.reversed().fold(0.toULong()) { acc, bracket ->
                    acc * 5u + opening.indexOf(bracket).toUInt().inc()
                }
            }
        }.awaitAll().filter { it > 0u }.sorted()
    }
    println(autocompletes[autocompletes.size / 2])
}