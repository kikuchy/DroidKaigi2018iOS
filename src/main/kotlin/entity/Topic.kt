package entity

data class Topic(
        val id: Int,
        val name: String
) {

    fun getNameByLang(lang: Lang): String = name
            .split(" / ")
            .getOrElse(lang.ordinal, { name })
            .trim()
}