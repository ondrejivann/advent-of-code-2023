import day03.Day03
import day04.Day04

fun main() {

    // test if implementation meets criteria from the description, like:
    val day04Test = Day04(readInput("Day04_test"))
    check(day04Test.totalWorthPoints == 13)
    check(day04Test.totalNumberOfScratchcards == 30)

    // print solution
    val day04 = Day04(readInput("Day04"))
    day04.getAnswer1().println()
    day04.getAnswer2().println()
}