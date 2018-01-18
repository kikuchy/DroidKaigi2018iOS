package sessionslist

import platform.Foundation.NSIndexPath
import platform.darwin.*
import platform.UIKit.*

class SessionsListDelegate(): NSObject(), UITableViewDelegateProtocol {
    override fun tableView(tableView: UITableView, didSelectRowAtIndexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(didSelectRowAtIndexPath, false)
    }
}