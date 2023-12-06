package day05

data class AlmanacRange(
        private val input: String
) {
    private val rangeData = input.trim().split(" ").map { it.toLong() }

    private val sourceRangeStart: Long = rangeData[1]
    private val destinationRangeStart: Long = rangeData[0]
    private val rangeLength: Long = rangeData[2]

    private val sourceRange: LongRange = sourceRangeStart.rangeUntil(sourceRangeStart + rangeLength)
    private val destinationRange: LongRange = destinationRangeStart.rangeUntil(destinationRangeStart + rangeLength)

    fun isSourceInSourceRange(source: Long) = source in sourceRange

    fun getDestinationFromSource(source: Long): Long {
        val indexInSourceRange = source - sourceRange.first
        return destinationRange.first + indexInSourceRange
    }
}