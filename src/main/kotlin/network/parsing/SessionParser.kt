package network.parsing

import entity.*
import entity.Session.SpeechSession
import extension.get
import extension.getInt
import extension.getString
import extension.map
import network.Category
import network.CategoryItem
import platform.Foundation.*

fun parseISO8601Date(raw: String): Date {
    val nsDate = NSDateFormatter().apply {
        dateFormat = "YYYY-MM-dd'T'HH:mm:ss"
        calendar = NSCalendar.currentCalendar
    }
            .dateFromString(raw)
    return parseDate(
            (nsDate!!.timeIntervalSince1970 * 1000.0).toLong()
    )
}

fun parseSession(
        rawSession: NSDictionary,
        speakerTable: List<Speaker>,
        roomTable: List<Room>,
        categoryTable: List<Category>
): SpeechSession {
    val sessionFormats: List<CategoryItem> = categoryTable.first { it.id!! == 808 }.items!!.filterNotNull()
    val languages: List<CategoryItem> = categoryTable.first { it.id!! == 809 }.items!!.filterNotNull()
    val topics: List<CategoryItem> = categoryTable.first { it.id!! == 811 }.items!!.filterNotNull()
    val levels: List<CategoryItem> = categoryTable.first { it.id!! == 824 }.items!!.filterNotNull()

    val categoryItems: List<Int> = rawSession.get<NSArray>("categoryItems").map<NSNumber, Int> { it.intValue }
    val roomId: Int = rawSession.getInt("roomId")
    val topic = topics.first { it.id == categoryItems[2] }
    val level = levels.first { it.id == categoryItems[3] }
    val speakerIds: List<String> = rawSession.get<NSArray>("speakers").map<NSString, String> { it.toString() }
    val startTime: Date = parseISO8601Date(rawSession.getString("startsAt"))
    return SpeechSession(
            id = rawSession.getString("id"),
            startTime = startTime,
            endTime = parseISO8601Date(rawSession.getString("endsAt")),
            dayNumber = 1/* TODO */,
            title = rawSession.getString("title"),
            desc = rawSession.getString("description"),
            room = roomTable.first { it.id == roomId },
            format = sessionFormats.first { it.id == categoryItems[0] }.name ?: "",
            language = languages.first { it.id == categoryItems[1] }.name ?: "",
            topic = Topic(topic.id!!, topic.name!!),
            level = Level(level.id!!, level.name!!),
            isFavorited = false,
            speakers = speakerIds.map { id -> speakerTable.first { it.id == id } }
    )
}