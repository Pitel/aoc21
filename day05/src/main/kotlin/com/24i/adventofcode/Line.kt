package com.`24i`.adventofcode

class Line(
    private val start: Pair<UShort, UShort>,
    private val end: Pair<UShort, UShort>
) {
    val isHorVer = start.first == end.first || start.second == end.second

    val points: Collection<Pair<UShort, UShort>> = when {
        start.first == end.first -> arrayOf(start.second, end.second)
            .apply { sort() }
            .let {
                it[0]..it[1]
            }
            .map {
                start.first to it.toUShort()
            }
        start.second == end.second -> arrayOf(start.first, end.first)
            .apply { sort() }
            .let {
                it[0]..it[1]
            }.map {
                it.toUShort() to start.second
            }
        else -> if (start.first < end.first) {
            start.first..end.first
        } else {
            start.first downTo end.first
        }.zip(
            if (start.second < end.second) {
                start.second..end.second
            } else {
                start.second downTo end.second
            }
        ).map {
            it.first.toUShort() to it.second.toUShort()
        }
    }

    override fun toString() = "$start -> $end"
}