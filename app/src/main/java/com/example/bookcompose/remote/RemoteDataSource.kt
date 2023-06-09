package com.example.bookcompose.remote

import com.example.bookcompose.core.network.BaseDataSource
import org.koin.dsl.module
import kotlin.math.max

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getBook(search: String) = getResult {
        apiService.getBook(
            searchQuery = search
        )
    }

    suspend fun getBooks(
        query: String,
        maxResult: Int
    ) = getResult {
        apiService.bookSearch(
            query, maxResult
        )
    }
}
