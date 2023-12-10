import day07.Day07

fun main() {
    // test if implementation meets criteria from the description, like:
    val day07Test = Day07(readInput("Day07_test"))
    check(day07Test.totalWinnings == 6440L)
    check(day07Test.wildcardTotalWinnings == 5905L)

    // print solution
    val day07 = Day07(readInput("Day07"))
    day07.getAnswer1().println()
    day07.getAnswer2().println()
}