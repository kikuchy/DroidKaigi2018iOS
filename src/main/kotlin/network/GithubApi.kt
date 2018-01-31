package network

import entity.Contributor
import extension.map
import kotlinx.cinterop.uncheckedCast
import network.parsing.parseContributor
import platform.Foundation.*
import platform.UIKit.UIApplication


fun getContributors(
        page: Int,
        onSuccess: (List<Contributor>) -> Unit,
        onFailure: (NetworkError) -> Unit
) {
    val session = NSURLSession.sessionWithConfiguration(
            NSURLSessionConfiguration.defaultSessionConfiguration,
            delegate = null,
            delegateQueue = NSOperationQueue.mainQueue
    )
    val url = NSURL(URLString = "https://api.github.com/repos/kikuchy/DroidKaigi2018iOS/contributors?per_page=100&page=$page")
    session.dataTaskWithURL(url) { data, _, error ->
        UIApplication.sharedApplication.networkActivityIndicatorVisible = false
        assert(NSThread.isMainThread)

        if (error != null) {
            // TODO: Classify errors to NetworkError.
            onFailure(NetworkError.TRAFFIC_PROBLEM)
            return@dataTaskWithURL
        }

        if (data == null) {
            onFailure(NetworkError.EMPTY_BODY)
            return@dataTaskWithURL
        }

        val deserializedData = NSJSONSerialization.JSONObjectWithData(data, options = 0, error = null)
        if (deserializedData == null) {
            onFailure(NetworkError.INVALID_JSON)
            return@dataTaskWithURL
        }

        val rawContributors = deserializedData.uncheckedCast<NSArray>()
        val contributors = rawContributors.map<NSDictionary, Contributor> { parseContributor(it) }
        onSuccess(contributors)
    }.resume()
    UIApplication.sharedApplication.networkActivityIndicatorVisible = true
}