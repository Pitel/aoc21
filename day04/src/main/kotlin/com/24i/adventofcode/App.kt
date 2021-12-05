package com.`24i`.adventofcode

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
        .lines()
        .filter { it.isNotBlank() }


    val draws = input.first().split(',').map { it.toUByte() }

    val boards = input.drop(1).windowed(5, 5).map { board ->
        Board(
            board.map { row ->
                row.split(' ').mapNotNull {
                    it.toUByteOrNull()
                }
            }
        )
    }

    run first@ {
        draws.forEachIndexed { i, draw ->
//        println("Draw ${i + 1}: $draw")
//        println()

            val win = boards.filter { it.draw(draw) }
//        println(boards.joinToString("\n\n"))
//        println()

            win.firstOrNull()?.also {
//                println("WINNER")
                println(it.score)
                return@first
            }
        }
    }

    run second@{
        var prevLosing = emptyList<Board>()
        draws.forEachIndexed { i, draw ->
//            println("Draw ${i + 1}: $draw")
//            println()

            val losing = boards.filterNot { it.draw(draw) }
//            println(losing.joinToString("\n\n"))
//            println()

            if (losing.isEmpty()) {
//                println("LAST")
                println(prevLosing.first().score)
                return@second
            }

            prevLosing = losing
        }
    }
}