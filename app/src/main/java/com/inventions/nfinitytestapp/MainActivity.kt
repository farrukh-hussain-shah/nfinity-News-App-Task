package com.inventions.nfinitytestapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
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

        binding.shimmerViewContainer.startShimmer()
        observeViewModel()
        newsViewModel.fetchNews("tesla", "2024-04-20", "publishedAt", "5dfc916111a84bb7b5cca3b79128fa69")

        setupSearchView()
    }

    private fun observeViewModel() {
        newsViewModel.news.observe(this) { articles ->
            newsAdapter.updateArticles(articles)  // Update the adapter with new articles
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = ShimmerFrameLayout.GONE
            binding.recyclerView.visibility = RecyclerView.VISIBLE
        }

        newsViewModel.error.observe(this) { errorMessage ->
            // Handle error (e.g., show a Toast)
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = ShimmerFrameLayout.GONE
            binding.recyclerView.visibility = RecyclerView.VISIBLE
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    newsViewModel.fetchNews(it, "2024-04-20", "publishedAt", "5dfc916111a84bb7b5cca3b79128fa69")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        binding.shimmerViewContainer.startShimmer()
                        binding.shimmerViewContainer.visibility = ShimmerFrameLayout.VISIBLE
                        binding.recyclerView.visibility = RecyclerView.GONE
                        newsViewModel.fetchNews(it, "2024-04-20", "publishedAt", "5dfc916111a84bb7b5cca3b79128fa69")
                    } else {
                        // Optional: Handle case when search text is cleared
                        newsAdapter.updateArticles(emptyList())
                        binding.shimmerViewContainer.stopShimmer()
                        binding.shimmerViewContainer.visibility = ShimmerFrameLayout.GONE
                        binding.recyclerView.visibility = RecyclerView.VISIBLE
                    }
                }
                return true
            }
        })
    }
}