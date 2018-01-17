package entity

data class Speaker(
        val id: String,
        var name: String,
        var tagLine: String,
        var imageUrl: String,
        var twitterUrl: String?,
        var githubUrl: String?,
        var blogUrl: String?,
        var companyUrl: String?
)