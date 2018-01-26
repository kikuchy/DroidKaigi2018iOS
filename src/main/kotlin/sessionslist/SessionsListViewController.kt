package sessionslist

import entity.Session
import fixeddata.SpecialSessions
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

        getSessions({ speakerSessions, _, _, _ ->
            val allSession = (speakerSessions!! + SpecialSessions.getSessions()).sortedBy { it.startTime.getTime().toLong() }
            sessionsTable.dataSource = SessionsListDataSource(allSession)
            sessionsTable.reloadData()
        }, { error ->
            println(error)
        })
    }

    override fun shouldPerformSegueWithIdentifier(identifier: String, sender: ObjCObject?): Boolean {
        return if (identifier == "ShowSession" && sender != null) {
            val selectedCell = sender.uncheckedCast<UITableViewCell>()
            val selectedPath = sessionsTable.indexPathForCell(selectedCell) ?: return false

            val session = sessionsTable.dataSource!!.uncheckedCast<SessionsListDataSource>().sessionAtIndexPath(selectedPath)
            return session is Session.SpeechSession
        } else
            super.shouldPerformSegueWithIdentifier(identifier, sender)
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