package day07

data class Hand(private val input: String) {

    private val handValue = input.substringBefore(" ")
    val cards = handValue.mapNotNull {
        Card.fromString(it.toString())
    }

    private val wildcardPositions = handValue.mapIndexedNotNull { index, c -> if (c == 'J') index else null}

    val wildcard = getWildcardHandType(handValue, wildcardPositions)

    val wildcards = wildcard.second.mapNotNull {
        Card.fromString(it.toString())
    }

    val bid: Long = input.substringAfter(" ").toLong()

    val type: HandType = getHandType(getHandMap(handValue))

    private fun getHandType(map: Map<Card, Int>): HandType {
        return when {
            map.containsValue(5) -> HandType.FIVE_OF_KIND
            map.containsValue(4) -> HandType.FOUR_OF_KIND
            map.containsValue(3) && map.containsValue(2) -> HandType.FULL_HOUSE
            map.containsValue(3) -> HandType.THREE_OF_KIND
            map.filterValues { it == 2 }.size == 2 -> HandType.TWO_PAIR
            map.containsValue(2) -> HandType.ONE_PAIR
            else -> HandType.HIGH_CARD
        }
    }

    private fun getWildcardHandType(handValue: String, wildcardPositions: List<Int>): Pair<HandType, String> {
        val map = getHandMap(handValue)
        var newHandValue = handValue

        if (handValue != "JJJJJ") {
            val wildcardMap = map
                    .filterKeys { it != Card.CARD_J }
                    .toList()
                    .sortedWith(compareByDescending<Pair<Card, Int>> { it.second }.thenBy { it.first.wildcardOrder })
                    .toMap()
                    .toMutableMap()

            wildcardPositions.forEach { wildcardPosition ->
                var numerousCard = 0
                val cardValue = wildcardMap.toList()[numerousCard].first.value.first()

                newHandValue = replaceCharAt(wildcardPosition, cardValue, newHandValue)
                while (wildcardMap.values.sum() != 5) {
                    val card = wildcardMap.toList()[numerousCard].first
                    val cardCount = wildcardMap.toList()[numerousCard].second
                    if (cardCount < 5) {
                        wildcardMap[card] = (wildcardMap[card] ?: 0) + 1
                    } else {
                        numerousCard += 1
                    }
                }
            }
            return (getHandType(wildcardMap) to newHandValue)
        }
        return (getHandType(map) to handValue)
    }

    private fun getHandMap(handValue: String): Map<Card, Int> {
        val map = mutableMapOf<Char, Int>()

        handValue.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        return map
                .mapKeys { Card.fromString(it.key.toString()) }
                .filterKeys { it != null }
                .mapKeys { it.key!! }
                .toList()
                .sortedBy { it.first.ordinal }
                .toMap()
    }

    private fun replaceCharAt(index: Int, ch: Char, str: String): String {
        if (index < 0 || index >= str.length) {
            return str // out of bounds
        }
        return str.substring(0, index) + ch + str.substring(index + 1)
    }
}