package day02

data class Game(private val input: String) {

    val id: Int = getGameId()

    private val subGames: List<SubGame> = getRawSubGames(getRawGame()).map { SubGame(it) }

    private val groupedSubGamesByMaxCountOfEachCubeColor = subGames
            .map { subGame -> subGame.groupedReveals }
            .flatMap { it.entries }
            .groupBy({ it.key }, { it.value })
            .mapValues { it.value.flatten() }
            .mapValues { it.value.maxOfOrNull { it.count } }

    val hasSubGameOverLimit: Boolean = subGames.any { it.hasRevealOverLimit }

    val minCubeCountMultiplication: Int = groupedSubGamesByMaxCountOfEachCubeColor.entries
            .mapNotNull { it.value }
            .reduce { acc, i -> acc * i }

    private fun getGameId(): Int = input.substringBefore(":").substringAfter("Game ").toInt()
    private fun getRawGame(): String = input.substringAfter(":")
    private fun getRawSubGames(rawGame: String): List<String> = rawGame.split(";")
}