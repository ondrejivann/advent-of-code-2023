package day04

import kotlin.math.pow

data class Scratchcard(private val input: String) {

    val id: String = input.substringBefore(":")

    private val winningNumbers: List<Int> = input
            .substringAfter(":")
            .substringBefore("|")
            .trim()
            .split(" ")
            .mapNotNull { it.toIntOrNull() }

    private val actualNumbers: List<Int> = input
            .substringAfter("|")
            .trim()
            .split(" ")
            .mapNotNull { it.toIntOrNull() }

    private val matchedNumbers: List<Int> = winningNumbers.intersect(actualNumbers.toSet()).toList()

    val matchedNumbersCount = matchedNumbers.size

    val points = if (matchedNumbers.isNotEmpty()) {
        (1 * 2.0.pow(matchedNumbers.size - 1)).toInt()
    } else {
        0
    }
}