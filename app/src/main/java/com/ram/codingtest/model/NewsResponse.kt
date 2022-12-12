package com.ram.codingtest.model

data class NewsResponse(
    val news: ArrayList<News>,
    val page: Int,
    val status: String
    )
