package com.emanuel.googlebooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.googlebooks.R
import com.emanuel.googlebooks.model.Volume
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BookListAdapter(
    private val items: List<Volume>,
    private val onItemClick: (Volume) -> Unit
) : RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book, parent, false)

        return BookHolder(layout)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val volume = items[position]
        if (volume.volumeInfo.imageLinks?.smallThumbnail != null) {
            Picasso.get().load(volume.volumeInfo.imageLinks?.smallThumbnail).into(holder.imgCover)
        } else {
            holder.imgCover.setImageResource(R.drawable.image_broken)
        }
        holder.txtTitle.text = volume.volumeInfo.title
        holder.txtAuthors.text = volume.volumeInfo.authors?.joinToString() ?: ""
        holder.txtPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
        holder.itemView.setOnClickListener {
            onItemClick(volume)
        }
    }

    override fun getItemCount(): Int = items.size

    class BookHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imgCover = rootView.imgCover
        val txtTitle = rootView.txtTitle
        val txtAuthors = rootView.txtAuthor
        val txtPages = rootView.txtPages
    }
}