package entity

import platform.Foundation.*

class Date(
        optTimeInMills: Long? = null
) {

    private val calendar = NSCalendar.currentCalendar

    val date: platform.Foundation.NSDate = optTimeInMills
            ?.let { NSDate.dateWithTimeIntervalSince1970(it.toDouble() / 1000.0) }
            ?: NSDate()

    fun getDate() = calendar.let {
        it.component(NSCalendarUnitDay, date)
    }

    fun getMonth() = calendar.let {
        it.component(NSCalendarUnitMonth, date)
    }

    fun getFullYear() = calendar.let {
        it.component(NSCalendarUnitYear, date)
    }

    fun getHours() = calendar.let {
        it.component(NSCalendarUnitHour, date)
    }

    fun getMinutes() = calendar.let {
        it.component(NSCalendarUnitMinute, date)
    }

    fun getTime(): Number = (date.timeIntervalSince1970 * 1000.0).toLong()

    override fun hashCode(): Int {
        return (date.timeIntervalSince1970 * 1000.0).toInt()
    }

    override fun equals(other: Any?): Boolean =
            other is Date && other.date.timeIntervalSince1970 == date.timeIntervalSince1970
}

fun Date.toReadableDateTimeString() = "${toReadableDateString()} ${toReadableTimeString()}"

fun readableDateFormat() = NSDateFormatter().apply { dateFormat = "MM/dd"; locale = NSLocale.currentLocale }
fun readableTimeFormat() = NSDateFormatter().apply { dateFormat = "HH:mm"; locale = NSLocale.currentLocale }
fun Date.toReadableDateString() = readableDateFormat().stringFromDate(date)
fun Date.toReadableTimeString() = readableTimeFormat().stringFromDate(date)
fun parseDate(timeInMills: Long): Date = Date(timeInMills)