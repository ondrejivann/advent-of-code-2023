import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/files/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

inline fun <T> List<T>.forEachIndexedWithPrevNext(action: (Int, T?, T, T?) -> Unit) {
    for (i in indices) {
        val prev: T? = getOrNull(i - 1)
        val curr: T = get(i)
        val next: T? = getOrNull(i + 1)
        action(i, prev, curr, next)
    }
}

inline fun <T, R> List<T>.mapIndexedWithNext(transform: (index: Int, item: T, next: T?) -> R): List<R> {
    return this.mapIndexed { index, item ->
        transform(index, item, this.getOrNull(index + 1))
    }
}

inline fun <T, R> List<T>.mapIndexedWithPrevNext(transform: (index: Int, prev: T?, item: T, next: T?) -> R): List<R> {
    return this.mapIndexed { index, item ->
        transform(index, this.getOrNull(index - 1) ,item, this.getOrNull(index + 1))
    }
}

// Functions to obtain the least common multiple and greatest common divisor generated from ChatGPT
fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun lcmOfList(numbers: List<Long>): Long = numbers.reduce { acc, i -> lcm(acc, i) }
