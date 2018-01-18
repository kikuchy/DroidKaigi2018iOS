package network.parsing

import entity.Speaker
import extension.get
import extension.getString
import extension.map
import platform.Foundation.NSArray
import platform.Foundation.NSDictionary

private data class Link(
        val title: String,
        val url: String,
        val linkType: String
)

fun parseSpeaker(rawData: NSDictionary): Speaker {
    val links: List<Link> = rawData
            .get<NSArray>("links")
            .map<NSDictionary, Link> {
                Link(
                        title = it.getString("title"),
                        url = it.getString("url"),
                        linkType = it.getString("linkType")
                )
            }
    return Speaker(
            id = rawData.getString("id"),
            name = rawData.getString("fullName"),
            tagLine = rawData.getString("tagLine"),
            imageUrl = rawData.getString("profilePicture"),
            twitterUrl = links.firstOrNull { it.linkType == "Twitter" }?.url,
            githubUrl = links.firstOrNull { it.title.toLowerCase() == "GitHub".toLowerCase() }?.url,
            blogUrl = links.firstOrNull { it.linkType == "Blog" }?.url,
            companyUrl = links.firstOrNull { it.linkType == "Company_Website" }?.url
    )
}