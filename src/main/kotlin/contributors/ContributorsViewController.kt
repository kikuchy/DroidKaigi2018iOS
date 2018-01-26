package contributors

import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCOutlet
import kotlinx.cinterop.initBy
import network.getContributors
import platform.CoreGraphics.CGRectGetHeight
import platform.Foundation.NSCoder
import platform.UIKit.*

@ExportObjCClass
class ContributorsViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet lateinit var tableView: UITableView

    override fun initWithCoder(aDecoder: NSCoder): UIViewController? = initBy(ContributorsViewController(aDecoder))

    private val delegate = ContributorsListDelegate()

    override fun viewDidLoad() {
        super.viewDidLoad()
        tableView.contentInset = UIEdgeInsetsMake(
                top = 64.0 /* = Status bar height + Navigation bar height  */,
                left = 0.0,
                bottom = tabBarController?.let { CGRectGetHeight(it.tabBar.frame) } ?: 0.0,
                right = 0.0
        )

        tableView.delegate = delegate

        getContributors(1, { contributors ->
            tableView.dataSource = ContributorsListDataSource(contributors)
            tableView.reloadData()
        }, { error ->
            println(error)
        })
    }
}