package day04


data class Day04(private val inputs: List<String>) {

    private val scratchcards = inputs.map { Scratchcard(it) }

    private val scratchCardTrees = scratchcards.map { ScratchcardTreeNode(it).createTree(scratchcards) }

    val totalWorthPoints = scratchcards.sumOf { it.points }

    val totalNumberOfScratchcards = scratchCardTrees.sumOf {
        var count = 0
        it.forEachDepthFirst { _ ->
            count++
        }
        count
    }

    fun getAnswer1(): String = "The total number of worth points is $totalWorthPoints"

    fun getAnswer2(): String = "The total number of scratchcards is $totalNumberOfScratchcards"
}


