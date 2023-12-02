package day02

data class Day02(val inputs: List<String>) {

    private val games: List<Game> = inputs.map { Game(it) }

    fun getAnswer1(): String = "The sum of the IDs of possible games is ${getPossibleGameIdsSum()}"

    fun getAnswer2(): String = "The sum of the power of sets is ${getMinimalCubeCountSetsSum()}"

    fun getPossibleGameIdsSum(): Int {
        val possibleGames = games.filterNot { it.hasSubGameOverLimit }
        return possibleGames.sumOf { it.id }
    }

    fun getMinimalCubeCountSetsSum(): Int {
        return games.sumOf { it.minCubeCountMultiplication }
    }
}