package day06


data class Day06(private val inputs: List<String>) {
    // V = S / T;   S = V * T;   T = S / V;
    //where V - speed, S - distance, T - time

    // Part One races
    private val races = mapInputsToRaces()

    // Part Two race
    private val race = mapInputsToRace()

    fun getAnswer1() = "You can beat the record in ${getWinningWaysCount()} ways"

    fun getAnswer2() = "For one race you can beat the record in ${getWinningWaysForBigRaceCount()} ways"

    fun getWinningWaysCount(): Long = races
            .map { it.betterRaceOptionsCount() }
            .reduce { acc, race -> acc * race }

    fun getWinningWaysForBigRaceCount(): Long = race.betterRaceOptionsCount()

    private fun mapInputsToRaces(): List<Race> {
        fun String.mapRowToLongList() = this
                .substringAfter(":")
                .trim()
                .split("\\s+".toRegex())
                .map { it.toLong() }

        val times = inputs
                .first()
                .mapRowToLongList()

        val distance = inputs
                .last()
                .mapRowToLongList()

        return times.zip(distance).map { Race(it.first, it.second) }
    }

    // For part two
    private fun mapInputsToRace(): Race {
        fun String.mapRowToLong() = this
                .substringAfter(":")
                .trim()
                .replace("\\s+".toRegex(), "")
                .toLong()

        return Race(inputs.first().mapRowToLong(), inputs.last().mapRowToLong())
    }
}
