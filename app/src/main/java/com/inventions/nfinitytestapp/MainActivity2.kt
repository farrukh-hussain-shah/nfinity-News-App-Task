package com.inventions.nfinitytestapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.inventions.nfinitytestapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // Enable edge-to-edge
       enableEdgeToEdge()

        // Use DataBindingUtil to set the content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)



        // Retrieve data from intent
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        Log.d("MainActivity2", "Author: $author")
        Log.d("MainActivity2", "Title: $title")
        val publishedAt = intent.getStringExtra("publishedAt")
        val description = intent.getStringExtra("description")
        val urlToImage = intent.getStringExtra("urlToImage")
        val content = intent.getStringExtra("content")

        // Display data in views
        binding.textDetailTitle.text = title
        binding.textDetailAuthor.text = "Author : $author"
        binding.textDetailTime.text = "Published at : $publishedAt"
        binding.textDetailDetail.text = description
        binding.textDetailContent.text = content
        Glide.with(this).load(urlToImage).into(binding.imgDetailNews)
    }
}