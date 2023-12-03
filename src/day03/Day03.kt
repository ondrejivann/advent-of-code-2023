package day03

data class Day03(private val inputs: List<String>) {

    private val engineScheme = EngineScheme(inputs = inputs)

    fun getAnswer1(): String = "The sum of all of the part numbers with adjacent symbols in the engine schematic is: ${getSumOfNumbersWithAdjacentSymbol()}"

    fun getAnswer2(): String = "The sum of all of the gear ratios in an engine schematic is ${getSumOfMultiplicationOfNumbersWithSharedAsterisk()}"

    fun getSumOfNumbersWithAdjacentSymbol(): Int = engineScheme.groupedNumbersWithAdjacentSymbol.keys.sumOf { it.value }

    fun getSumOfMultiplicationOfNumbersWithSharedAsterisk(): Int {
        val numbersWithAsteriskSymbol = engineScheme.groupedNumbersWithAdjacentSymbol.filterValues { it.isAsteriskSymbol }
        return numbersWithAsteriskSymbol.entries.mapNotNull { i ->
            val secondValue = numbersWithAsteriskSymbol.entries.find { j ->
                i.key != j.key && i.value == j.value
            }
            secondValue?.let { Triple(i.key, secondValue.key, i.value) }
        }.distinctBy { it.third }.sumOf { it.first.value * it.second.value }
    }
}
