package extension

import kotlinx.cinterop.uncheckedCast
import platform.Foundation.*

inline fun <T, R> NSArray.map(transform: (T) -> R): List<R> {
    val result: MutableList<R> = mutableListOf()
    var i = 0L
    while (i < count) {
        val elem = objectAtIndex(i).uncheckedCast<T>()
        result.add(transform(elem))
        i++
    }
    return result
}