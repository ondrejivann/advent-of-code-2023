package day09

import forEachIndexedWithPrevNext
import mapIndexedWithNext

data class OASIS(private val input: String) {

    private val history = input.split(" ").map { it.toInt() }

    val extrapolatedNextValue = extrapolateNextValue()

    val extrapolatedBackwardValue = extrapolateBackwardValue()

    private fun extrapolateNextValue(): Int {
        val allDiffLines = getDiffLines()

        return allDiffLines
                .reversed()
                .map { it.last() }
                .reduce { acc, i -> acc + i }
    }

    private fun extrapolateBackwardValue(): Int {
        val allDiffLines = getDiffLines()
        val extrapolatedBackwardLowerValues = mutableListOf(0)

        allDiffLines
                .map { it.first() }
                .reversed()
                .forEachIndexedWithPrevNext { index, prev, curr, next ->
                    next?.let {
                        val newExtrapolatedBackwardLowerLevelValue = next - extrapolatedBackwardLowerValues.last()
                        extrapolatedBackwardLowerValues.add(newExtrapolatedBackwardLowerLevelValue)
                    }
                }

        return extrapolatedBackwardLowerValues.last()
    }

    private fun getDiffLines(): List<List<Int>> {
        val allDiffLines = mutableListOf<List<Int>>().apply { add(history) }
        val lastDiffLine = mutableListOf<Int>().apply { addAll(history) }

        while (!lastDiffLine.all { it == 0 }) {
            val diffList = lastDiffLine.mapIndexedWithNext { index, curr, next ->
                next?.let {
                    it - curr
                }
            }.filterNotNull()
            allDiffLines.add(diffList)
            lastDiffLine.apply {
                clear()
                addAll(diffList)
            }
        }

        return allDiffLines
    }
}