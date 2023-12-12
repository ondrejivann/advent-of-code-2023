package day08

data class Day08(private val inputs: List<String>) {
    private val network = DesertNetwork(inputs)

    val numberOfRequiredSteps = network.numberOfRequiredSteps

    val numberOfRequiredStepsMultipleVertexes = network.numberOfRequiredStepsMultipleVertexes

    fun getAnswer1() = "To reach ZZZ ${network.numberOfRequiredSteps} steps are required."

    fun getAnswer2() = "It will take ${network.numberOfRequiredStepsMultipleVertexes} steps to stay only on nodes that end with Z."
}