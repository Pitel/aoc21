package com.`24i`.adventofcode

class Board(input: List<List<UByte>>) {
    private val inputEnhanced: List<List<NumberData>> = input.map { row ->
        row.map {
            NumberData(it)
        }
    }

    var lastDraw: UByte = 0u

    val win: Boolean
        get() = inputEnhanced.any { row ->
            row.all {
                it.drawn
            }
        } or inputEnhanced.transpose().any { row ->
            row.all {
                it.drawn
            }
        }

    val score: ULong
        get() = lastDraw * inputEnhanced.flatten()
            .filterNot { it.drawn }
            .sumOf { it.number.toULong() }

    fun draw(num: UByte): Boolean {
        inputEnhanced.flatten().find { it.number == num }?.drawn = true
        lastDraw = num
        return win
    }

    private fun <T> List<List<T>>.transpose(): List<List<T>> = List(first().size) { i ->
        List(size) { j ->
            this[j][i]
        }
    }

    private data class NumberData(
        val number: UByte,
        var drawn: Boolean = false
    )

    override fun toString(): String = inputEnhanced.joinToString("\n") { row ->
        row.joinToString(" ") {
            if (it.drawn) {
                "*${it.number}*"
            } else {
                "${it.number}"
            }
        }
    }
}