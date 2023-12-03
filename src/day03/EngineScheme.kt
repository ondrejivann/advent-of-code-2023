package day03

data class EngineScheme(private val inputs: List<String>) {

    private val scheme: List<SchemeItem> = inputs.mapToScheme()

    private val numbers = scheme.filterIsInstance<Number>()
    private val symbols = scheme.filterIsInstance<Symbol>()

    val groupedNumbersWithAdjacentSymbol: Map<Number, Symbol> = numbers.mapNotNull { number ->
        val symbol = symbols.find { symbol ->
            symbol.schemeItemPosition.x.first in number.xBorderRange && symbol.schemeItemPosition.y in number.yBorderRange
        }
        symbol?.let { number to symbol }
    }.toMap()

    private fun List<String>.mapToScheme(): List<SchemeItem> {
        val scheme = mutableListOf<SchemeItem>()

        forEachIndexed { rowIndex, row ->
            var number = ""
            var dots = ""
            row.forEachIndexed { index, c ->
                when {
                    c.isDigit() -> {
                        number += c
                        if (dots != "") {
                            scheme.add(Dot(dots, schemeItemPosition = SchemeItemPosition(IntRange(index - dots.length, index - 1), rowIndex)))
                            dots = ""
                        }
                        if (index == row.lastIndex) {
                            scheme.add(Number(number, schemeItemPosition = SchemeItemPosition(IntRange(index - number.length, index - 1), rowIndex)))
                        }
                    }

                    c == '.' -> {
                        dots += c
                        if (number != "") {
                            scheme.add(Number(number, schemeItemPosition = SchemeItemPosition(IntRange(index - number.length, index - 1), rowIndex)))
                            number = ""
                        }
                        if (index == row.lastIndex) {
                            scheme.add(Dot(dots, schemeItemPosition = SchemeItemPosition(IntRange(index - dots.length, index - 1), rowIndex)))
                        }
                    }

                    else -> {
                        if (dots != "") {
                            scheme.add(Dot(dots, schemeItemPosition = SchemeItemPosition(IntRange(index - dots.length, index - 1), rowIndex)))
                            dots = ""
                        }
                        if (number != "") {
                            scheme.add(Number(number, schemeItemPosition = SchemeItemPosition(IntRange(index - number.length, index - 1), rowIndex)))
                            number = ""
                        }
                        scheme.add(Symbol(c.toString(), SchemeItemPosition(IntRange(index, index), rowIndex)))
                    }
                }
            }
        }

        return scheme
    }
}