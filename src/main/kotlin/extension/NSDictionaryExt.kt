package extension

import kotlinx.cinterop.uncheckedCast
import platform.Foundation.*

fun NSDictionary.getInt(key: String): Int {
    return valueForKey(key).uncheckedCast<NSNumber>().intValue
}

fun NSDictionary.getString(key: String): String {
    return valueForKey(key).uncheckedCast<NSString>().toString()
}

operator fun <T> NSDictionary.get(key: String): T {
    return valueForKey(key).uncheckedCast()
}

fun <T> NSDictionary.getOrNull(key: String): T? {
    return valueForKey(key)?.uncheckedCast()
}