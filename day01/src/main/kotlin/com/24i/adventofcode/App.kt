package com.`24i`.adventofcode

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun main() {
    println(withContext(Dispatchers.Default) { "hello" })
}