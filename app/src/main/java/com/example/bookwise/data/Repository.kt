package com.example.bookwise.data

import com.example.bookwise.model.Book
import com.example.bookwise.network.ApiService

interface Repository {
    suspend fun getBooks(): List<Book>
}

class NetworkRepository(private val apiService: ApiService) : Repository {
    override suspend fun getBooks(): List<Book>  = apiService.getBooks()
}