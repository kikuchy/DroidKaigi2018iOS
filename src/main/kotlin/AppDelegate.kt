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

        UINavigationBar.appearance().apply {
            barTintColor = UIColor(red = 251.0 / 256.0, green = 200.0 / 256.0, blue = 18.0 / 256.0, alpha = 1.0)
            tintColor = UIColor.whiteColor
        }
        UITabBar.appearance().apply {
            barTintColor = UIColor(red = 251.0 / 256.0, green = 200.0 / 256.0, blue = 18.0 / 256.0, alpha = 1.0)
            tintColor = UIColor.whiteColor
            unselectedItemTintColor = UIColor(white = 0.4, alpha = 1.0)
        }
        return true
    }

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }
}