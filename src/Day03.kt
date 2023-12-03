import day03.Day03

fun main() {

    // test if implementation meets criteria from the description, like:
    val day03Test = Day03(readInput("Day03_test"))
    check(day03Test.getSumOfNumbersWithAdjacentSymbol() == 4361)
    check(day03Test.getSumOfMultiplicationOfNumbersWithSharedAsterisk() == 467835)

    // print solution
    val day03 = Day03(readInput("Day03"))
    day03.getAnswer1().println()
    day03.getAnswer2().println()
}