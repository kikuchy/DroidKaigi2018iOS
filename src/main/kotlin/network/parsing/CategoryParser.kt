package network.parsing

import extension.*
import network.Category
import network.CategoryItem
import platform.Foundation.*

private fun parseCategoryItem(rawData: NSDictionary): CategoryItem {
    return CategoryItem(
            id = rawData.getInt("id"),
            sort = rawData.getInt("sort"),
            name = rawData.getString("name")
    )
}

fun parseCategory(rawData: NSDictionary): Category {
    val items: List<CategoryItem>? = rawData
            .getOrNull<NSArray>("items")
            ?.map<NSDictionary, CategoryItem> {
                parseCategoryItem(it)
            }
    return Category(
            id = rawData.getInt("id"),
            sort = rawData.getInt("sort"),
            title = rawData.getString("title"),
            items = items
    )
}