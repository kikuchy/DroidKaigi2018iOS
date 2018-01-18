package sessionslist

import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCOutlet
import kotlinx.cinterop.initBy
import network.getSessions
import platform.CoreGraphics.CGRectGetHeight
import platform.Foundation.NSCoder
import platform.UIKit.*

@ExportObjCClass
class SessionsListViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet lateinit var sessionsTable: UITableView

    override fun initWithCoder(aDecoder: NSCoder): UIViewController? = initBy(SessionsListViewController(aDecoder))

    override fun viewDidLoad() {
        super.viewDidLoad()
        // FIXME: It's not elegant.
        sessionsTable.contentInset = UIEdgeInsetsMake(
                top = 64.0 /* = Status bar height + Navigation bar height  */,
                left = 0.0,
                bottom = tabBarController?.let { CGRectGetHeight(it.tabBar.frame) } ?: 0.0,
                right = 0.0
        )

        getSessions({ sessions, _, _, _ ->
            sessionsTable.dataSource = SessionsListDataSource(sessions!!)
            sessionsTable.reloadData()
        }, { error ->
            println(error)
        })
    }
}