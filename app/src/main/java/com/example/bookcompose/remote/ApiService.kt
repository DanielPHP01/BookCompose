package com.example.bookcompose.remote

import com.example.bookcompose.remote.model.BookModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    suspend fun getBook(
        @Query("q") searchQuery: String,
    ): Response<BookModelDto.Item>

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): Response<BookModelDto>
}