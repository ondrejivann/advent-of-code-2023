package day02

data class SubGame(private val input: String) {

    private val reveals: List<Reveal> = getRawReveals().map { Reveal(it) }

    val hasRevealOverLimit: Boolean = reveals.any { it.count > it.cubeColor.max }

    val groupedReveals: Map<CubeColor, List<Reveal>> = reveals.groupBy { it.cubeColor }

    private fun getRawReveals(): List<String> = input.split(",")
}