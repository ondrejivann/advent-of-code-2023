package day03

sealed class SchemeItem(val rawValue: String, val schemeItemPosition: SchemeItemPosition)

class Number(rawValue: String, schemeItemPosition: SchemeItemPosition) : SchemeItem(rawValue, schemeItemPosition) {
    val value = rawValue.toInt()

    // range contains schemeItemPosition like -1/-1, but it does not matter in the end
    val yBorderRange = IntRange((schemeItemPosition.y - 1), (schemeItemPosition.y + 1))
    val xBorderRange = IntRange((schemeItemPosition.x.first - 1), (schemeItemPosition.x.last + 1))
}

class Symbol(rawValue: String, schemeItemPosition: SchemeItemPosition) : SchemeItem(rawValue, schemeItemPosition) {
    val isAsteriskSymbol = rawValue == "*"
}

class Dot(rawValue: String, schemeItemPosition: SchemeItemPosition) : SchemeItem(rawValue, schemeItemPosition)