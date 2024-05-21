package com.inventions.nfinitytestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    // LiveData for the news articles
    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> get() = _news

    // LiveData for error messages
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Function to fetch news articles
    fun fetchNews(query: String, from: String, sortBy: String, apiKey: String) {
        viewModelScope.launch {
            val response = repository.fetchNews(query, from, sortBy, apiKey)
            if (response != null) {
                _news.postValue(response.articles)  // Update LiveData with articles
            } else {
                _error.postValue("Failed to fetch news")  // Update LiveData with error message
            }
        }
    }
}
