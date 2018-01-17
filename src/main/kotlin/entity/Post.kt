package entity


data class Post(
        val title: String,
        val content: String,
        val date: Date,
        val published: Boolean,
        val type: String
)