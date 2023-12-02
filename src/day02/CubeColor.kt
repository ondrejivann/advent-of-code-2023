package day02

enum class CubeColor(val max: Int, val regex: Regex) {
    RED(12, "\\d{1,2} red".toRegex()),
    GREEN(13, "\\d{1,2} green".toRegex()),
    BLUE(14, "\\d{1,2} blue".toRegex())
    ;
}