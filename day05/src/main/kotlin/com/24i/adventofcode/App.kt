package com.`24i`.adventofcode

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            line.split("""[^\d]+""".toRegex())
                .map { it.toUShort() }
        }
        .map {
            Line(
                it[0] to it[1],
                it[2] to it[3]
            )
        }

    println(Line(1.toUShort() to 1.toUShort(), 3.toUShort() to 3.toUShort()).points)
    println(Line(9.toUShort() to 7.toUShort(), 7.toUShort() to 9.toUShort()).points)

    val danger = input
        .filter { it.isHorVer }
        .fold(mutableListOf<Pair<UShort, UShort>>()) { acc, line ->
            acc.apply {
                addAll(line.points)
            }
        }
        .groupingBy { it }
        .eachCount()
        .filterValues { it >= 2 }
        .size
    println(danger)

    val danger2 = input
        .fold(mutableListOf<Pair<UShort, UShort>>()) { acc, line ->
            acc.apply {
                addAll(line.points)
            }
        }
        .groupingBy { it }
        .eachCount()
        .filterValues { it >= 2 }
        .size
    println(danger2)
}