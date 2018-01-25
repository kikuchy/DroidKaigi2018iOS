package map

import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.initBy
import kotlinx.cinterop.readValue
import libs.GMSCameraPosition
import libs.GMSMapView
import libs.GMSMarker
import platform.CoreGraphics.CGRectZero
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class MapViewController(aDecoder: NSCoder): UIViewController(aDecoder) {

    override fun initWithCoder(aDecoder: NSCoder): UIViewController? = initBy(MapViewController(aDecoder))

    override fun viewDidLoad() {
        super.viewDidLoad()
        val camera = GMSCameraPosition.cameraWithLatitude(latitude = 35.696031, longitude = 139.690523, zoom = 16.0f)
        val mapView = GMSMapView.mapWithFrame(CGRectZero.readValue(), camera).apply {
            myLocationEnabled = true
        }
        view = mapView

        GMSMarker().apply {
            position = CLLocationCoordinate2DMake(35.696031, 139.690523)
            title = "Bellesalle Shinjuku Grand Conference Center"
            snippet = "Sumitomo Fudosan Shinjuku Grand Tower 5F\n" +
                    "8-17-3 Nishi Shinjuku, Shinjuku City, Tokyo 160-0023"
            map = mapView
        }
    }
}