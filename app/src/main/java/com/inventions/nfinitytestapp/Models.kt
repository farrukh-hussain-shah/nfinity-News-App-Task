package com.inventions.nfinitytestapp

// Article.kt
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

// NewsResponse.kt
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
