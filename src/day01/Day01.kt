package day01

class Day01(private val input: List<String>) {

    private val digitsMap = mapOf(
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
    )

    // region PART 1
    fun getAnswer1(): String = "The sum of calibration values is ${getCalibration()}"

    fun getCalibration(): Int = input.sumOf {
        runCatching {
            val firstDigitChar = it.firstDigit()
            val lastDigitChar = it.lastDigit()
            "$firstDigitChar$lastDigitChar".toInt()
        }.getOrDefault(0)
    }

    private fun String.firstDigit(): Char = first { it.isDigit() }

    private fun String.lastDigit(): Char = last { it.isDigit() }
    // endregion

    // region PART 2
    fun getAnswer2(): String = "The sum of calibration values with spelled out letters is ${getSpelledOutCalibrationValue()}"
    fun getSpelledOutCalibrationValue(): Int = input.sumOf { row ->
        getRowNumber(row)
    }

    private fun getRowNumber(row: String): Int {
        val firstDigit = findFirstDigit(row)
        val lastDigit = findLastDigit(row)

        return "$firstDigit$lastDigit".ifBlank { "0" }.toInt()
    }

    private fun findLastDigit(row: String): Char? {
        val digitIndexedDigit = findLastCharDigitWithIndex(row)
        val spelledIndexedDigit = findLastSpelledDigitWithIndex(row)
        return listOfNotNull(digitIndexedDigit, spelledIndexedDigit).maxByOrNull { it.second }?.first
    }

    private fun findFirstDigit(row: String): Char? {
        val digitIndexedDigit = findFirstCharDigitWithIndex(row)
        val spelledIndexedDigit = findFirstSpelledDigitWithIndex(row)
        return listOfNotNull(digitIndexedDigit, spelledIndexedDigit).minByOrNull { it.second }?.first
    }

    private fun findFirstCharDigitWithIndex(row: String): Pair<Char, Int>? = row
            .withIndex()
            .firstOrNull { it.value.isDigit() }
            ?.let { it.value to it.index }

    private fun findLastCharDigitWithIndex(row: String): Pair<Char, Int>? = row
            .withIndex()
            .lastOrNull { it.value.isDigit() }
            ?.let { it.value to it.index }

    private fun findFirstSpelledDigitWithIndex(row: String): Pair<Char, Int>? = digitsMap.keys
            .map { it to row.indexOf(it) }
            .filterNot { it.second == -1 }
            .mapNotNull { pair -> digitsMap[pair.first]?.let { it to pair.second } }
            .minByOrNull { it.second }

    private fun findLastSpelledDigitWithIndex(row: String): Pair<Char, Int>? = digitsMap.keys
            .map { it to row.lastIndexOf(it) }
            .filterNot { it.second == -1 }
            .mapNotNull { pair -> digitsMap[pair.first]?.let { it to pair.second } }
            .maxByOrNull { it.second }
    // endregion
}