package com.example.bookcompose.objects

import com.example.bookcompose.remote.model.BookModelDto

data class MainState(
    val isLoading:Boolean=false,
    val data:List<BookModelDto> = emptyList(),
    val error:String=""
)
