package com.emanuel.googlebooks.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object BookHttp {

    //chave da API
    private const val API_KEY = "AIzaSyCaAKw40Vrfi1lzwsFb7CAtytTXdUsCtTQ"
    //url da API para a consulta dos livros
    private const val BOOK_JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key$API_KEY"

    //instância o cliente que faz a conexão com o servidor OkHttp
    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchBook(q: String): SearchResult? {
        //faz a requisição na api com o nome do livro recebeu como parâmetro
        val request = Request.Builder()
            .url(String.format(BOOK_JSON_URL, q))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()

            return Gson().fromJson(json, SearchResult::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}