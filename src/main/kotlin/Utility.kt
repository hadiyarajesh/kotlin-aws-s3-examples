import java.text.StringCharacterIterator
import kotlin.math.sign

fun getHumanReadableByteCount(bytes: Long): String {
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(bytes)
    if (absB < 1024) {
        return "$bytes B"
    }
    var value = absB
    val characterIterator = StringCharacterIterator("KMGTPE")

    for (i in 40 downTo 0) {
        if (absB <= 0xfffccccccccccccL shr i) {
            break
        }
        value = value shr 10
        characterIterator.next()
    }

    value *= bytes.sign
    return "%.1f %cB".format(value.toDouble() / 1024.0, characterIterator.current())
}
