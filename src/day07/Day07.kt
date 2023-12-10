package day07

data class Day07(private val inputs: List<String>) {

    private val hands: List<Hand> = inputs.map { Hand(it) }

    private val orderedHands = hands
            .sortedWith { o1, o2 ->
                var c = o1.type.compareTo(o2.type)
                if (c == 0 && o1.cards.size == o2.cards.size) {
                    for (i in o1.cards.indices) {
                        c = o1.cards[i].compareTo(o2.cards[i])
                        if (c != 0) {
                            break
                        }
                    }
                }
                c
            }

    private val wildcardOrderedHands = hands
            .sortedWith { o1, o2 ->
                var c = o1.wildcard.first.ordinal.compareTo(o2.wildcard.first.ordinal)
                if (c == 0 && o1.cards.size == o2.wildcards.size) {
                    for (i in o1.wildcards.indices) {
                        c = o1.cards[i].wildcardOrder.compareTo(o2.cards[i].wildcardOrder)
                        if (c != 0) {
                            break
                        }
                    }
                }
                c
            }

    val totalWinnings = orderedHands
            .reversed()
            .mapIndexed { index, hand ->
                (index + 1) * hand.bid
            }
            .sum()



    val wildcardTotalWinnings = wildcardOrderedHands
            .reversed()
            .mapIndexed { index, hand ->
                (index + 1) * hand.bid
            }
            .sum()

    fun getAnswer1() = "Total winning are $totalWinnings"

    fun getAnswer2() = "Total wildcard winning are $wildcardTotalWinnings"
}

