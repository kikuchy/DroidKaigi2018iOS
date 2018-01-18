package sessionslist

import entity.Session
import platform.Foundation.NSIndexPath
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewDataSourceProtocol
import platform.UIKit.row
import platform.darwin.NSInteger
import platform.darwin.NSObject

@Suppress("CONFLICTING_OVERLOADS")
class SessionsListDataSource(initialSessions: List<Session> = emptyList()): NSObject(), UITableViewDataSourceProtocol {

    private val sessions: MutableList<Session> = mutableListOf<Session>().apply { addAll(initialSessions) }

    fun sessionAtIndexPath(path: NSIndexPath): Session {
        return sessions[path.row.toInt()]
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return sessions.size.toLong()
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        val session = sessionAtIndexPath(cellForRowAtIndexPath)
        return tableView.dequeueReusableCellWithIdentifier("Session", cellForRowAtIndexPath).apply {
            textLabel?.text = when (session) {
                is Session.SpeechSession -> session.title
                is Session.SpecialSession -> session.title.toString()
            }
            detailTextLabel?.text = when (session) {
                is Session.SpeechSession -> "${session.speakers.first().name} -- ${session.room.name}"
                is Session.SpecialSession -> session.room?.name ?: ""
            }
        }
    }
}