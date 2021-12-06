package com.`24i`.adventofcode

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .first()
        .split(',')
        .map { it.toULong() }
    println(input)

    val state = MutableList(9) { i ->
        input.count { i.toULong() == it }.toULong()
    }
    println(state)

    repeat(256) {
        val new = state.removeFirst()
        state[6] += new
        state.add(new)
        println("${state.sum()} $state")
    }
}