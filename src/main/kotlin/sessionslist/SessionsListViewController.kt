package sessionslist

import kotlinx.cinterop.*
import network.getSessions
import platform.CoreGraphics.CGRectGetHeight
import platform.Foundation.NSCoder
import platform.UIKit.*
import sessiondetail.SessionDetailViewController

@ExportObjCClass
class SessionsListViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet lateinit var sessionsTable: UITableView

    override fun initWithCoder(aDecoder: NSCoder): UIViewController? = initBy(SessionsListViewController(aDecoder))

    private val sessionsListDelegate = SessionsListDelegate()

    override fun viewDidLoad() {
        super.viewDidLoad()
        // FIXME: It's not elegant.
        sessionsTable.contentInset = UIEdgeInsetsMake(
                top = 64.0 /* = Status bar height + Navigation bar height  */,
                left = 0.0,
                bottom = tabBarController?.let { CGRectGetHeight(it.tabBar.frame) } ?: 0.0,
                right = 0.0
        )
        sessionsTable.delegate = sessionsListDelegate

        getSessions({ sessions, _, _, _ ->
            sessionsTable.dataSource = SessionsListDataSource(sessions!!)
            sessionsTable.reloadData()
        }, { error ->
            println(error)
        })
    }

    override fun prepareForSegue(segue: UIStoryboardSegue, sender: ObjCObject?) {
        super.prepareForSegue(segue, sender)

        if (segue.identifier == "ShowSession" && sender != null) {
            val selectedCell = sender.uncheckedCast<UITableViewCell>()
            val selectedPath = sessionsTable.indexPathForCell(selectedCell) ?: return

            val detailViewController = segue.destinationViewController.uncheckedCast<SessionDetailViewController>()
            val session = sessionsTable.dataSource!!.uncheckedCast<SessionsListDataSource>().sessionAtIndexPath(selectedPath)
            detailViewController.sessionToShow = session
        }
    }
}