package com.inventions.nfinitytestapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.text_description)
        private val image: ImageView = itemView.findViewById(R.id.image_headline)
        private val date: TextView = itemView.findViewById(R.id.publishDate)
        private val author: TextView = itemView.findViewById(R.id.author)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Article) {
            title.text = article.title
            description.text = article.description
            Glide.with(itemView.context).load(article.urlToImage).into(image)
            date.text = article.publishedAt
            author.text = article.author
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val article = articles[position]
                val intent = Intent(context, MainActivity2::class.java).apply {
                    putExtra("title", article.title)
                    putExtra("author", article.author)
                    putExtra("publishedAt", article.publishedAt)
                    putExtra("description", article.description)
                    putExtra("urlToImage", article.urlToImage)
                    putExtra("content", article.content)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_items_recyclerview, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
