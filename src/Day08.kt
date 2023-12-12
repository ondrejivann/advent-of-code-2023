import day08.Day08

fun main() {
    val day08Test = Day08(readInput("Day08_test"))
    check(day08Test.numberOfRequiredSteps == 2L)

    // print solution
    val day08 = Day08(readInput("Day08"))
    day08.getAnswer1().println()
    day08.getAnswer2().println()
}