package contributors

import entity.Contributor
import libs.sd_setImageWithURL
import platform.Foundation.NSIndexPath
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.darwin.NSObject
import platform.UIKit.UITableViewDataSourceProtocol
import platform.UIKit.row
import platform.darwin.NSInteger

class ContributorsListDataSource(val contributors: List<Contributor>): NSObject(), UITableViewDataSourceProtocol {
    fun contributorAtIndexPath(indexPah: NSIndexPath): Contributor {
        return contributors[indexPah.row.toInt()]
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return contributors.size.toLong()
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        val contributor = contributorAtIndexPath(cellForRowAtIndexPath)
        return tableView.dequeueReusableCellWithIdentifier("Contributor", cellForRowAtIndexPath).apply {
            textLabel?.text = contributor.name
            // TODO: Show avatar image of contributor.
        }

    }
}