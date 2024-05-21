package com.inventions.nfinitytestapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inventions.nfinitytestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository(RetrofitClient.instance.create(NewsApiService::class.java)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this, listOf())
        binding.recyclerView.adapter = newsAdapter

        observeViewModel()
        newsViewModel.fetchNews("tesla", "2024-04-20", "publishedAt", "5dfc916111a84bb7b5cca3b79128fa69")
    }

    private fun observeViewModel() {
        newsViewModel.news.observe(this) { articles ->
            newsAdapter.updateArticles(articles)
        }

        newsViewModel.error.observe(this) { errorMessage ->
            // Handle error (e.g., show a Toast)
        }
    }
}
