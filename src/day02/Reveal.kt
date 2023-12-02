package day02

data class Reveal(private val input: String) {

    private val trimmedInput = input.trim()

    val cubeColor: CubeColor = CubeColor.entries.find { trimmedInput.matches(it.regex) } ?: error("Cube color not found")

    val count: Int = trimmedInput.split(" ").first().toInt()
}