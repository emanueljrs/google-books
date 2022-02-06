package com.emanuel.googlebooks.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emanuel.googlebooks.R
import com.emanuel.googlebooks.model.Volume
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        val volume = intent.getParcelableExtra<Volume>(EXTRA_BOOK)

        if (volume != null) {
            if (volume.volumeInfo.imageLinks?.smallThumbnail != null) {
                Picasso.get().load(volume.volumeInfo.imageLinks?.smallThumbnail).into(imgCover)
            } else {
                imgCover.setImageResource(R.drawable.image_broken)
            }
            txtTitle.text = volume.volumeInfo.title
            txtAuthor.text = volume.volumeInfo.authors?.joinToString() ?: ""
            txtPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
            txtPublisher.text = volume.volumeInfo.publisher
            txtDatePublisher.text = volume.volumeInfo.publishedDate
            txtDescription.text = volume.volumeInfo.description
        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_BOOK = "book"

        fun open(context: Context, volume: Volume) {
            val detailIntent = Intent(context, BookDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_BOOK, volume)
            context.startActivity(detailIntent)
        }
    }
}