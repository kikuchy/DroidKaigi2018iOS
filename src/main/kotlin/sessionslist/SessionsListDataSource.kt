package sessionslist

import entity.Date
import entity.Session
import entity.toReadableDateTimeString
import platform.Foundation.NSIndexPath
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewDataSourceProtocol
import platform.UIKit.UITableViewCellAccessoryNone
import platform.UIKit.UITableViewCellAccessoryDisclosureIndicator
import platform.UIKit.row
import platform.UIKit.section
import platform.darwin.NSInteger
import platform.darwin.NSObject

@Suppress("CONFLICTING_OVERLOADS")
class SessionsListDataSource(initialSessions: List<Session> = emptyList()): NSObject(), UITableViewDataSourceProtocol {

    private val groupedSessions: List<Pair<Date, List<Session>>> =
            initialSessions.groupBy { it.startTime }.toList().sortedBy { it.first.getTime().toLong() }

    fun sessionAtIndexPath(path: NSIndexPath): Session {
        return groupedSessions[path.section.toInt()].second[path.row.toInt()]
    }

    override fun numberOfSectionsInTableView(tableView: UITableView): NSInteger {
        return groupedSessions.size.toLong()
    }

    override fun tableView(tableView: UITableView, titleForHeaderInSection: NSInteger): String? {
        return groupedSessions[titleForHeaderInSection.toInt()].first.toReadableDateTimeString()
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return groupedSessions[numberOfRowsInSection.toInt()].second.size.toLong()
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
            accessoryType = when (session) {
                is Session.SpeechSession -> UITableViewCellAccessoryDisclosureIndicator
                is Session.SpecialSession -> UITableViewCellAccessoryNone
            }
        }
    }
}