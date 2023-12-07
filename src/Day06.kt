import day06.Day06

fun main() {
    // test if implementation meets criteria from the description, like:
    val day06Test = Day06(readInput("Day06_test"))
    check(day06Test.getWinningWaysCount() == 288L)
    check(day06Test.getWinningWaysForBigRaceCount() == 71503L)

    // print results
    val day06 = Day06(readInput("Day06"))
    day06.getAnswer1().println()
    day06.getAnswer2().println()
}