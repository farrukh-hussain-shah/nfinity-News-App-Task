package com.inventions.nfinitytestapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val apiService: NewsApiService) {

    suspend fun fetchNews(query: String, from: String, sortBy: String, apiKey: String): NewsResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getNews(query, from, sortBy, apiKey)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
