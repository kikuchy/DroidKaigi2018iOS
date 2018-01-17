package sessionslist

import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCOutlet
import kotlinx.cinterop.initBy
import platform.Foundation.NSCoder
import platform.UIKit.UIEdgeInsetsMake
import platform.UIKit.UITableView
import platform.UIKit.UIViewController

@ExportObjCClass
class SessionsListViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet lateinit var sessionsTable: UITableView

    override fun initWithCoder(aDecoder: NSCoder): UIViewController? = initBy(SessionsListViewController(aDecoder))

    override fun viewDidLoad() {
        super.viewDidLoad()
        // FIXME: It's not elegant.
        sessionsTable.contentInset = UIEdgeInsetsMake(64.0 /* = Status bar height + Navigation bar height  */, 0.0, 0.0, 0.0)
        sessionsTable.dataSource = SessionsListDataSource()
    }
}