import day09.Day09

fun main() {
    // check test values
    val day09Test = Day09(readInput("Day09_test"))
    check(day09Test.sumOfExtrapolatedNextValues == 114)
    check(day09Test.sumOfExtrapolatedBackwardValues == 2)

    // print solution
    val day09 = Day09(readInput("Day09"))
    day09.getAnswer1().println()
    day09.getAnswer2().println()
}