package day05

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

data class Almanac(private val inputs: List<String>) {

    private val seeds: List<Long> = inputs
            .first()
            .substringAfter(":")
            .trim()
            .split(" ")
            .map { it.toLong() }

    private val seedRanges = seeds
            .windowed(size = 2, step = 2, partialWindows = false)
            .map { it[0].rangeUntil(it[0] + it[1]) }

    private val maps: List<AlmanacMap> = inputs
            .takeLast(inputs.size - 2)
            .fold(mutableListOf(mutableListOf<String>())) { acc, s ->
                if (s.trim().isNotEmpty()) {
                    acc.last().add(s)
                } else {
                    acc.add(mutableListOf())
                }
                acc
            }.map {
                AlmanacMap(it)
            }

    suspend fun getFinalDestinationForAllSeeds() = coroutineScope {
        seeds.map { seed ->
            async(Dispatchers.IO) {
                getFinalDestination(seed)
            }
        }.awaitAll().min()
    }

    suspend fun getFinalDestinationForAllSeedRanges() = coroutineScope {
        seedRanges.asFlow().map { seedRange ->
            seedRange.asFlow().map { seed ->
                withContext(Dispatchers.Default) {
                    getFinalDestination(seed)
                }
            }.toList().min()
        }
    }.toList().min()

    private fun getFinalDestination(seed: Long): Long {
        return maps.fold(seed) { previous, map ->
            map.getDestination(previous)
        }
    }
}