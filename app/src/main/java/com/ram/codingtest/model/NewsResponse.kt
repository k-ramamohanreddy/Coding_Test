package com.ram.codingtest.model

data class NewsResponse(
    val news: MutableList<News>,
    val page: Int,
    val status: String
    )
