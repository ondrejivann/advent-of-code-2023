import day05.Day05
import kotlinx.coroutines.runBlocking

fun main() {

    // test if implementation meets criteria from the description, like:
    val day05Test = Day05(readInput("Day05_test"))

    runBlocking {
        check(day05Test.getSeedsLocationNumber() == 35L)
        check(day05Test.getSeedRangesLocationNumber() == 46L)
    }

    // print solution
    val day05 = Day05(readInput("Day05"))

    runBlocking {
        day05.getSeedsLocationNumber().println()
        day05.getSeedRangesLocationNumber().println()
    }
}