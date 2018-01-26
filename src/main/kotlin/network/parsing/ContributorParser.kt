package network.parsing

import entity.Contributor
import extension.getInt
import extension.getString
import platform.Foundation.NSDictionary

fun parseContributor(raw: NSDictionary): Contributor {
    return Contributor(
            raw.getString("login"),
            null,
            raw.getString("avatar_url"),
            raw.getString("html_url"),
            raw.getInt("contributions")
    )
}