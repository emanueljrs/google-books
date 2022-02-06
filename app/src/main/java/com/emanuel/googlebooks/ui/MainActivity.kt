package com.emanuel.googlebooks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.googlebooks.R
import com.emanuel.googlebooks.model.BookHttp
import com.emanuel.googlebooks.model.Volume
import com.emanuel.googlebooks.ui.adapter.BookListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerviewBooks.layoutManager = LinearLayoutManager(this)

        //Cria uma courotine para atualizar as informações na main thread
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook("Dominando o Android")
            }
            result?.items?.let {
                recyclerviewBooks.adapter = BookListAdapter(it, this@MainActivity::openBookDetail)
            }
        }
    }

    private fun openBookDetail(volume: Volume) {
        BookDetailActivity.open(this, volume)
    }
}