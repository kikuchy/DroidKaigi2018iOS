package network

import entity.Room
import entity.Session
import entity.Speaker
import extension.get
import extension.map
import kotlinx.cinterop.uncheckedCast
import network.parsing.parseCategory
import network.parsing.parseRoom
import network.parsing.parseSession
import network.parsing.parseSpeaker
import platform.Foundation.*


data class Category(
        val id: Int?,
        val sort: Int?,
        val title: String?,
        val items: List<CategoryItem?>?
)

data class CategoryItem(
        val name: String?,
        val id: Int?,
        val sort: Int?
)

fun getSessions(
        onSuccess: (List<Session>?, List<Room>?, List<Speaker>?, List<Category>?) -> Unit,
        onFailure: (NetworkError) -> Unit
) {
    val session = NSURLSession.sessionWithConfiguration(
            NSURLSessionConfiguration.defaultSessionConfiguration,
            delegate = null,
            delegateQueue = NSOperationQueue.mainQueue
    )
    val url = NSURL(URLString = "https://droidkaigi.jp/2018/sessionize/all.json")
    session.dataTaskWithURL(url) { data, _, error ->
        assert(NSThread.isMainThread)

        if (error != null) {
            // TODO: Classify errors to NetworkError.
            onFailure(NetworkError.TRAFFIC_PROBLEM)
            return@dataTaskWithURL
        }

        if (data == null) {
            onFailure(NetworkError.EMPTY_BODY)
            return@dataTaskWithURL
        }

        val deserializedData = NSJSONSerialization.JSONObjectWithData(data, options = 0, error = null)
        if (deserializedData == null) {
            onFailure(NetworkError.INVALID_JSON)
            return@dataTaskWithURL
        }

        val rawRoot = deserializedData.uncheckedCast<NSDictionary>()
        val rawRooms: NSArray = rawRoot["rooms"]
        val rooms = rawRooms.map<NSDictionary, Room> { parseRoom(it) }
        val rawSpeakers: NSArray = rawRoot["speakers"]
        val speakers = rawSpeakers.map<NSDictionary, Speaker> { parseSpeaker(it) }
        val rawCategories: NSArray = rawRoot["categories"]
        val categories = rawCategories.map<NSDictionary, Category> { parseCategory(it) }
        val rawSessions: NSArray = rawRoot["sessions"]
        val sessions: List<Session> = rawSessions.map<NSDictionary, Session> { parseSession(it, speakers, rooms, categories) }
        onSuccess(sessions, rooms, speakers, categories)
    }.resume()
}