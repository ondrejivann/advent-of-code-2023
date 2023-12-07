package day06

data class Race(
        val time: Long, // T - milliseconds
        val distanceRecord: Long, // S - millimeters
) {
    fun betterRaceOptionsCount(): Long {
        var betterRaceOptionsCounter = 0L
        for (milliseconds in 1 until time) {
            val distance = countDistance(milliseconds, time)
            if (distance > distanceRecord) {
                betterRaceOptionsCounter++
            }
        }
        return betterRaceOptionsCounter
    }

    private fun countDistance(holdTime: Long, raceTime: Long): Long {
        // hold time is equal to speed
        val actualRaceTime = raceTime - holdTime
        return holdTime * actualRaceTime
    }
}