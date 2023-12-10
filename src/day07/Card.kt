package day07

enum class Card(val value: String, val wildcardOrder: Int) {
    CARD_A("A", 1),
    CARD_K("K", 2),
    CARD_Q("Q", 3),
    CARD_J("J", 13),
    CARD_T("T", 4),
    CARD_9("9", 5),
    CARD_8("8", 6),
    CARD_7("7", 7),
    CARD_6("6", 8),
    CARD_5("5", 9),
    CARD_4("4", 10),
    CARD_3("3", 11),
    CARD_2("2", 12),
    ;

    companion object {
        fun fromString(value: String): Card? {
            for (card in entries) {
                if (card.value == value) {
                    return card
                }
            }
            return null
        }
    }
}