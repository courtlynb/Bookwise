package com.example.bookwise.network

import com.example.bookwise.model.Book
import retrofit2.http.GET

interface ApiService  {
    @GET("volumes?q=hiphop")
    suspend fun getBooks(): List<Book>
}