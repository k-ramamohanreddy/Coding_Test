package com.ram.codingtest.api

import com.ram.codingtest.api.RequestConstants.API_KEY
import com.ram.codingtest.api.RequestConstants.LANGUAGE_CODE
import com.ram.codingtest.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIInterface {

    @GET("v1/latest-news?")
    suspend fun getNews(@Header("Authorization") authorization:String = API_KEY,
                        @Query("languages") languages: String = LANGUAGE_CODE): Response<NewsResponse>
}