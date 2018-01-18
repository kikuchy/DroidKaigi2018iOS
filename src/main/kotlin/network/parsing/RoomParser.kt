package network.parsing

import entity.Room
import extension.getInt
import extension.getString
import platform.Foundation.NSDictionary

fun parseRoom(rawData: NSDictionary): Room {
    return Room(
            id = rawData.getInt("id"),
            name = rawData.getString("name")
    )
}