package contributors

import kotlinx.cinterop.uncheckedCast
import platform.Foundation.NSIndexPath
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UITableView
import platform.UIKit.UITableViewDelegateProtocol
import platform.darwin.NSObject

class ContributorsListDelegate(): NSObject(), UITableViewDelegateProtocol {
    override fun tableView(tableView: UITableView, didSelectRowAtIndexPath: NSIndexPath) {
        val source = tableView.dataSource?.uncheckedCast<ContributorsListDataSource>()
        println(source)
        val contributor = source?.contributorAtIndexPath(didSelectRowAtIndexPath)
        println(contributor)
        contributor?.let {
            UIApplication.sharedApplication.openURL(NSURL.URLWithString(it.htmlUrl)!!)
        }
        tableView.deselectRowAtIndexPath(didSelectRowAtIndexPath, false)
    }
}