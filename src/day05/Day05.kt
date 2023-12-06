package day05

data class Day05(private val inputs: List<String>) {

    private val almanac = Almanac(inputs)

    suspend fun getSeedsLocationNumber() = almanac.getFinalDestinationForAllSeeds()

    suspend fun getSeedRangesLocationNumber() = almanac.getFinalDestinationForAllSeedRanges()
}