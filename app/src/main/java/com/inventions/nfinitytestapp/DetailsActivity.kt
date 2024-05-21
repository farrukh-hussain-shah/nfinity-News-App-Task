import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.inventions.nfinitytestapp.R
import com.inventions.nfinitytestapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        // Retrieve data from intent
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val publishedAt = intent.getStringExtra("publishedAt")
        val description = intent.getStringExtra("description")
        val urlToImage = intent.getStringExtra("urlToImage")
        val content = intent.getStringExtra("content")

        // Display data in views
        binding.textDetailTitle.text = title
        Log.d("DetailsActivity", "title: $title")
        binding.textDetailAuthor.text = author
        Log.d("DetailsActivity", "publishedAt: $author")
        binding.textDetailTime.text = publishedAt
        binding.textDetailDetail.text = description
        binding.textDetailContent.text = content
        Glide.with(this).load(urlToImage).into(binding.imgDetailNews)
    }
}
