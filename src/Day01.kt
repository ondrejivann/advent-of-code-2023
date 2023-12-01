import day01.Day01

fun main() {

    // test if implementation meets criteria from the description, like:
    val day01Test1 = Day01(readInput("Day01_part_1_test"))
    check(day01Test1.getCalibration() == 142)
    val day01Test2 = Day01(readInput("Day01_part_2_test"))
    check(day01Test2.getSpelledOutCalibrationValue() == 281)

    // print solution
    val day01 = Day01(readInput("Day01"))
    day01.getAnswer1().println()
    day01.getAnswer2().println()
}
