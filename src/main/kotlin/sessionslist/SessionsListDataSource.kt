package sessionslist

import platform.Foundation.NSIndexPath
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewDataSourceProtocol
import platform.darwin.NSInteger
import platform.darwin.NSObject

@Suppress("CONFLICTING_OVERLOADS")
class SessionsListDataSource: NSObject(), UITableViewDataSourceProtocol {

    private val sessions: MutableList<String> = mutableListOf(
    )

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return sessions.size.toLong()
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        return tableView.dequeueReusableCellWithIdentifier("Session", cellForRowAtIndexPath).apply {
            textLabel?.text = TODO("Session Title")
            detailTextLabel?.text = TODO("Speakers and Place")
        }
    }
}