import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.initBy
import kotlinx.cinterop.uncheckedCast
import libs.GMSServices
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class AppDelegate : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta {}

    override fun init() = initBy(AppDelegate())

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: NSDictionary?): Boolean {
        val key = NSBundle.mainBundle.objectForInfoDictionaryKey("GoogleMapsAPIKey")?.uncheckedCast<NSString>().toString()
        GMSServices.provideAPIKey(key)
        return true
    }

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }
}