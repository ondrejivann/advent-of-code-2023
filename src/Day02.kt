import day02.Day02

fun main() {

    // test if implementation meets criteria from the description, like:
    val day02Test = Day02(readInput("Day02_test"))
    check(day02Test.getPossibleGameIdsSum() == 8)
    check(day02Test.getMinimalCubeCountSetsSum() == 2286)

    // print solution
    val day02 = Day02(readInput("Day02"))
    day02.getAnswer1().println()
    day02.getAnswer2().println()
}