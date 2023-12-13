package day09

data class Day09(private val inputs: List<String>) {
    private val measurements = inputs.map { OASIS(it) }

    val sumOfExtrapolatedNextValues = measurements.sumOf { it.extrapolatedNextValue }
    val sumOfExtrapolatedBackwardValues = measurements.sumOf { it.extrapolatedBackwardValue }

    fun getAnswer1() = "Sum of all extrapolated next values is $sumOfExtrapolatedNextValues"
    fun getAnswer2() = "Sum of all extrapolated backward values is $sumOfExtrapolatedBackwardValues"
}
